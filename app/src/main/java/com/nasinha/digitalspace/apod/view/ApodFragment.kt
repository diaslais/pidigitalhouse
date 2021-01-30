package com.nasinha.digitalspace.apod.view

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.apod.model.ApodResponseModel
import com.nasinha.digitalspace.apod.repository.ApodRepository
import com.nasinha.digitalspace.apod.viewmodel.ApodViewModel
import com.nasinha.digitalspace.favorite.db.AppDatabase
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity
import com.nasinha.digitalspace.favorite.entity.UserEntity
import com.nasinha.digitalspace.favorite.repository.FavoriteRepository
import com.nasinha.digitalspace.favorite.viewmodel.FavoriteViewModel
import com.nasinha.digitalspace.favorite.viewmodel.FavoriteViewModelFactory
import com.nasinha.digitalspace.utils.ApodUtils.getIdVideo
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.Constants.APP_KEY
import com.nasinha.digitalspace.utils.Constants.IMGAPOD
import com.nasinha.digitalspace.utils.Constants.SWITCH_PREFS
import com.nasinha.digitalspace.utils.DrawerUtils.lockDrawer
import com.nasinha.digitalspace.utils.FavoriteUtils
import com.nasinha.digitalspace.utils.TranslateUtils.options
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.OnBalloonClickListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class ApodFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _apodResponse: ApodResponseModel
    private lateinit var _favoriteViewModel: FavoriteViewModel
    private lateinit var englishPortugueseTranslator: Translator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lockDrawer(requireActivity())
        _view = view
        val imgLoad = _view.findViewById<ImageView>(R.id.imgApod)
        val txtExplanation = _view.findViewById<TextView>(R.id.txtExplanationApod)
        val txtTitle = _view.findViewById<TextView>(R.id.txtTitle)

        val viewModel = ViewModelProvider(
            this, ApodViewModel.ApodViewModelFactory(
                ApodRepository()
            )
        ).get(ApodViewModel::class.java)

        addFavoriteViewModel()

        viewModel.getDataApod().observe(viewLifecycleOwner, {

            try {
                mostrarResultados(it as ApodResponseModel, view)
            } catch (e: Exception) {
                showLoading(true)
                showShareFavorite(false)

                e.message
                Picasso.get()
                    .load(R.drawable.gatinho)
                    .into(imgLoad, object : Callback {
                        override fun onSuccess() {
                            showLoading(false)
                        }

                        override fun onError(e: java.lang.Exception?) {
                            TODO("Not yet implemented")
                        }

                    })
                txtTitle.text = getString(R.string.apod_error)
                txtExplanation.text =
                    getString(R.string.apod_message)
            }

        })

        backPressed()
        showLoading(true)
        showShareFavorite(false)
    }

    private fun addFavoriteViewModel() {
        _favoriteViewModel = ViewModelProvider(
            this,
            FavoriteViewModelFactory(
                FavoriteRepository(
                    AppDatabase.getDatabase(_view.context).favoriteDao()
                )
            )
        ).get(FavoriteViewModel::class.java)
    }


    private fun mostrarResultados(it: ApodResponseModel, view: View) {
        showLoading(false)
        showShareFavorite(true)

        val imgLoad = _view.findViewById<ImageView>(R.id.imgApod)
        val txtExplanation = _view.findViewById<TextView>(R.id.txtExplanationApod)
        val txtTitle = _view.findViewById<TextView>(R.id.txtTitle)
        val prefs = activity?.getSharedPreferences(APP_KEY, AppCompatActivity.MODE_PRIVATE)
        val btnTranlateALert = _view.findViewById<ImageButton>(R.id.btnTranslateAlert)
        val youTubePlayerView: YouTubePlayerView = _view.findViewById(R.id.youtube_player_view)
        val mediaType = it.media_type
        val checkPrefs = prefs?.getBoolean(SWITCH_PREFS, false)
        _apodResponse = it

        _favoriteViewModel.checkFavorite(requireActivity(), it.url).observe(viewLifecycleOwner, {
            favoriteIsActive(it)
            btnFavorite()
        })

        if (checkPrefs == true) {

            btnTranlateALert.visibility = View.GONE

            if (mediaType == "video") {

                imgLoad.visibility = View.GONE
                youTubePlayerView.visibility = View.VISIBLE

                lifecycle.addObserver(youTubePlayerView)
                youTubePlayerView.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        val videoId = getIdVideo(it.url)
                        youTubePlayer.loadVideo(videoId, 1F)
                        youTubePlayer.play()
                    }
                })
            }

            englishPortugueseTranslator = Translation.getClient(options())

            lifecycle.addObserver(englishPortugueseTranslator)
            englishPortugueseTranslator.translate(it.title)
                .addOnSuccessListener {
                    txtTitle.text = it
                }
                .addOnFailureListener {
                    txtTitle.text = _apodResponse.title
                }

            lifecycle.addObserver(englishPortugueseTranslator)
            englishPortugueseTranslator.translate(it.explanation)
                .addOnSuccessListener {

                    txtExplanation.text = it + getText(R.string.quebra_linha)

                }.addOnFailureListener {
                    txtExplanation.text = _apodResponse.explanation + getText(R.string.quebra_linha)
                }

            Picasso.get()
                .load(it.url)
                .into(imgLoad, object : Callback {
                    override fun onSuccess() {
                        showLoading(false)
                    }

                    override fun onError(e: java.lang.Exception?) {
                        TODO("Not yet implemented")
                    }

                })
        } else {

            btnTranlateALert.visibility = View.VISIBLE

            if (mediaType == "video") {
                imgLoad.visibility = View.GONE
                youTubePlayerView.visibility = View.VISIBLE

                lifecycle.addObserver(youTubePlayerView)
                youTubePlayerView.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        val videoId = getIdVideo(it.url)
                        youTubePlayer.loadVideo(videoId, 1F)
                        youTubePlayer.play()
                    }
                })
            }

            txtTitle.text = it.title
            txtExplanation.text = it.explanation + getText(R.string.quebra_linha)

            Picasso.get()
                .load(it.url)
                .into(imgLoad, object : Callback {
                    override fun onSuccess() {
                        showLoading(false)
                    }

                    override fun onError(e: java.lang.Exception?) {
                        TODO("Not yet implemented")
                    }

                })
        }
        landScapeMode(it.url)
        showTranslateAlert()
    }

    private fun favoriteIsActive(isChecked: Boolean) {
        val btnAddFavorite = _view.findViewById<CheckBox>(R.id.cbFavoriteApod)
        btnAddFavorite.isChecked = isChecked
    }

    private fun landScapeMode(urlImg: String) {
        val imgLoad = _view.findViewById<ImageView>(R.id.imgApod)
        val navController = NavHostFragment.findNavController(this)

        imgLoad.setOnClickListener {
            val bundle = bundleOf(IMGAPOD to urlImg)
            navController.navigate(R.id.action_apodFragment_to_landsScapeApodFragment, bundle)
        }

    }


    private fun btnFavorite() {
        val btnAddFavorite = _view.findViewById<CheckBox>(R.id.cbFavoriteApod)
        val userId = AuthUtil.getUserId(requireActivity().application)!!

        btnAddFavorite.setOnCheckedChangeListener { _, isChecked ->
            val favorite = FavoriteEntity(
                image = _apodResponse.url,
                title = _apodResponse.title,
                "",
                text = _apodResponse.explanation,
                "",
                date = _apodResponse.date,
                type = _apodResponse.media_type
            )
            val user = UserEntity(
                image = _apodResponse.url,
                userId = AuthUtil.getUserId(requireActivity())!!
            )
            if (isChecked) {
                _favoriteViewModel.addFavorite(favorite).observe(viewLifecycleOwner, {})
                _favoriteViewModel.addUserFavorite(user).observe(viewLifecycleOwner, {})
            } else {
                _favoriteViewModel.deleteFavoriteItem(favorite.image, userId)
                    .observe(viewLifecycleOwner, { })
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        val progressBar = _view.findViewById<LinearLayout>(R.id.llProgressApod)
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showShareFavorite(isShown: Boolean) {
        val checkBoxFavorite = _view.findViewById<CheckBox>(R.id.cbFavoriteApod)
        val shareButton = _view.findViewById<ImageButton>(R.id.ibShareFavoriteItem)
        checkBoxFavorite.visibility = if (isShown) View.VISIBLE else View.GONE
        shareButton.visibility = if (isShown) View.VISIBLE else View.GONE
        shareButton.setOnClickListener {
            val text = _view.findViewById<TextView>(R.id.txtExplanationApod).text.toString()
            shareHandler(text)
        }
    }

    private fun shareHandler(text: String) {
        lifecycleScope.launch {
            val imageBitmap = FavoriteUtils.getBitmapFromView(_view, _apodResponse.url)
            activity?.let { FavoriteUtils.shareImageText(it, _view, imageBitmap, text) }
        }
    }

    private fun showTranslateAlert() {
        val btnTranslate = _view.findViewById<ImageButton>(R.id.btnTranslateAlert)
        val balloon = Balloon.Builder(_view.context)
            .setArrowSize(10)
            .setArrowOrientation(ArrowOrientation.BOTTOM)
            .setArrowVisible(true)
            .setWidthRatio(1.0f)
            .setHeight(70)
            .setTextSize(15f)
            .setArrowPosition(0.73f)
            .setCornerRadius(4f)
            .setAlpha(0.9f)
            .setText("Opção de tradução disponível no menu configurações.")
            .setTextColor(ContextCompat.getColor(_view.context, R.color.colorBlack))
            .setBackgroundColor(ContextCompat.getColor(_view.context, R.color.colorWhite))
            .setOnBalloonClickListener(OnBalloonClickListener { })
            .setBalloonAnimation(BalloonAnimation.FADE)
            .setLifecycleOwner(viewLifecycleOwner)
            .build()

        btnTranslate.setOnClickListener {
            balloon.show(it)
            balloon.showAlignBottom(it)
            balloon.dismissWithDelay(1000L)
        }

    }

    private fun backPressed() {
        val btnToolbar = _view.findViewById<MaterialToolbar>(R.id.apodTopAppBar)
        btnToolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }
}