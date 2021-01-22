package com.nasinha.digitalspace.favorite.view

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.favorite.db.AppDatabase
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity
import com.nasinha.digitalspace.favorite.repository.FavoriteRepository
import com.nasinha.digitalspace.favorite.viewmodel.FavoriteViewModel
import com.nasinha.digitalspace.favorite.viewmodel.FavoriteViewModelFactory
import com.nasinha.digitalspace.utils.ApodUtils
import com.nasinha.digitalspace.utils.Constants.APP_KEY
import com.nasinha.digitalspace.utils.Constants.IMAGE
import com.nasinha.digitalspace.utils.Constants.SWITCH_PREFS
import com.nasinha.digitalspace.utils.Constants.TITLE
import com.nasinha.digitalspace.utils.Constants.VIDEO
import com.nasinha.digitalspace.utils.FavoriteUtils
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class FavoriteScreenFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _favoriteViewModel: FavoriteViewModel
    private lateinit var _favorite: FavoriteEntity
    private var _translatePrefs: Boolean? = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view
        addViewModel()
        translatePrefHandler()
        backBtnHandler()
        argumentsHandler()
    }

    private fun addViewModel() {
        _favoriteViewModel = ViewModelProvider(
            this,
            FavoriteViewModelFactory(
                FavoriteRepository(
                    AppDatabase.getDatabase(_view.context).favoriteDao()
                )
            )
        ).get(FavoriteViewModel::class.java)
    }

    private fun translatePrefHandler() {
        val prefs =
            requireActivity().getSharedPreferences(APP_KEY, MODE_PRIVATE)
        _translatePrefs = prefs?.getBoolean(SWITCH_PREFS, false)
    }

    private fun backBtnHandler() {
        val backBtn = _view.findViewById<ImageButton>(R.id.ibBackFavoriteScreen)
        backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun argumentsHandler() {
        val imageArgument = arguments?.getString(IMAGE)!!

        _favoriteViewModel.getFavorite(imageArgument).observe(viewLifecycleOwner, {
            addInfoToFavorite(it)
        })
    }

    private fun addInfoToFavorite(favorite: FavoriteEntity) {
        val imageView = _view.findViewById<ImageView>(R.id.ivImageFavoriteScreen)
        val youTubePlayerView = _view.findViewById<YouTubePlayerView>(R.id.ypYoutubePlayerFavorite)
        val dateView = _view.findViewById<TextView>(R.id.tvDateFavoriteScreen)
        val titleView = _view.findViewById<TextView>(R.id.tvTitleFavoriteScreen)
        val textView = _view.findViewById<TextView>(R.id.tvTextFavoriteScreen)

        titleView.text = if (_translatePrefs == true) favorite.titleBr else favorite.title
        textView.text = if (_translatePrefs == true) favorite.textBr else favorite.text

        dateView.text = FavoriteUtils.dateModifier(favorite.date)

        when (favorite.type) {
            IMAGE -> {
                Picasso.get().load(favorite.image).into(imageView)
                imageClickHandler(imageView, favorite.image, titleView)
                shareButton(favorite.image)
            }
            VIDEO -> {
                imageView.visibility = View.GONE
                youTubePlayerView.visibility = View.VISIBLE
                youTubePlayerHandler(youTubePlayerView, favorite.image)
            }
        }
    }

    private fun imageClickHandler(
        imageView: ImageView,
        imageArgument: String,
        titleText: TextView,
    ) {
        imageView.setOnClickListener {
            val navController = findNavController()
            val bundle = bundleOf(IMAGE to imageArgument, TITLE to titleText.text.toString())
            navController.navigate(
                R.id.action_favoriteScreenFragment_to_favoriteImageFragment,
                bundle
            )
        }
    }

    private fun shareButton(imageArgument: String) {
        val shareBtn = _view.findViewById<ImageButton>(R.id.ibShareFavoriteScreen)
        shareBtn.setOnClickListener {

            lifecycleScope.launch {
                val imageBitmap = FavoriteUtils.getBitmapFromView(_view, imageArgument)
                val text = _view.findViewById<TextView>(R.id.tvTextFavoriteScreen).text.toString()
                activity?.let { FavoriteUtils.shareImageText(it, _view, imageBitmap, text) }
            }

        }
    }

    private fun youTubePlayerHandler(youTubePlayerView: YouTubePlayerView, image: String) {
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                val videoId = ApodUtils.getIdVideo(image)
                youTubePlayer.loadVideo(videoId, 1F)
                youTubePlayer.play()
            }
        })
    }
}