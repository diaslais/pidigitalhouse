package com.nasinha.digitalspace.favorite.view

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.utils.Constants.IMAGE
import com.nasinha.digitalspace.utils.Constants.TITLE
import com.nasinha.digitalspace.utils.FavoriteUtils
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class FavoriteImageFragment : Fragment() {
    private lateinit var _view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            requireActivity().window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view
        argumentsHandler()
        closeBtnHandler()
    }

    private fun argumentsHandler() {
        val imageArgument = arguments?.getString(IMAGE)!!
        val titleArgument = arguments?.getString(TITLE)
        val toolbar = _view.findViewById<LinearLayout>(R.id.llToolbarFavoriteImage)
        val imageView = _view.findViewById<ImageView>(R.id.ivImageFavoriteImage)
        val titleView = _view.findViewById<TextView>(R.id.tvTitleFavoriteImage)

        Picasso.get().load(imageArgument).resize(600, 600).into(imageView)
        if (!titleArgument.isNullOrEmpty()) {
            titleView.text = titleArgument
        }

        shareBtnHandler(imageArgument)

        imageView.setOnClickListener {
            when (toolbar.visibility) {
                View.VISIBLE -> toolbar.visibility = View.GONE
                View.GONE -> toolbar.visibility = View.VISIBLE
            }
        }
    }

    private fun shareBtnHandler(imageArgument: String) {
        val shareBtn = _view.findViewById<ImageButton>(R.id.ibShareFavoriteImage)

        shareBtn.setOnClickListener {
            lifecycleScope.launch {
                val imageBitmap = FavoriteUtils.getBitmapFromView(_view, imageArgument)
                FavoriteUtils.shareImageText(requireActivity(), _view, imageBitmap, null)
            }
        }
    }

    private fun closeBtnHandler() {
        val closeView = _view.findViewById<ImageButton>(R.id.ibCloseFavoriteImage)
        closeView.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}