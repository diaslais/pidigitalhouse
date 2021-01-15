package com.nasinha.digitalspace.favorite.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.favorite.utils.FavoriteConstants.DATE
import com.nasinha.digitalspace.favorite.utils.FavoriteConstants.IMAGE
import com.nasinha.digitalspace.favorite.utils.FavoriteConstants.TEXT
import com.nasinha.digitalspace.favorite.utils.FavoriteConstants.TITLE
import com.nasinha.digitalspace.favorite.utils.FavoriteConstants.TYPE
import com.nasinha.digitalspace.favorite.utils.FavoriteConstants.VIDEO
import com.nasinha.digitalspace.favorite.utils.FavoriteUtils
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class FavoriteScreenFragment : Fragment() {
    private lateinit var _view: View

    val options = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.PORTUGUESE)
        .build()

    private val englishPortugueseTranslator = Translation.getClient(options)

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
        backBtnHandler()
        argumentsHandler()
    }

    private fun backBtnHandler() {
        val backBtn = _view.findViewById<ImageButton>(R.id.ibBackFavoriteScreen)
        backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun argumentsHandler() {
        val imageArgument = arguments?.getString(IMAGE)!!
        val titleArgument = arguments?.getString(TITLE)
        val textArgument = arguments?.getString(TEXT)
        val dateArgument = arguments?.getString(DATE)!!
        val typeArgument = arguments?.getString(TYPE)!!

        val imageView = _view.findViewById<ImageView>(R.id.ivImageFavoriteScreen)
        val dateView = _view.findViewById<TextView>(R.id.tvDateFavoriteScreen)
        val titleView = _view.findViewById<TextView>(R.id.tvTitleFavoriteScreen)
        val textView = _view.findViewById<TextView>(R.id.tvTextFavoriteScreen)

        checkPrefsListener(titleArgument, textArgument, titleView, textView)

        dateView.text = dateArgument

        when (typeArgument) {
            IMAGE -> {
                Picasso.get().load(imageArgument).into(imageView)
                imageClickHandler(imageView, imageArgument, titleView)
                shareButton(imageArgument)
            }
            VIDEO -> {
                Toast.makeText(_view.context, "é video sô!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkPrefsListener(
        titleArgument: String?,
        textArgument: String?,
        titleView: TextView,
        textView: TextView
    ) {

        val titleArgumentTranslate = if (titleArgument.isNullOrEmpty()) "" else titleArgument
        val textArgumentTranslate = if (textArgument.isNullOrEmpty()) "" else textArgument

        val prefs =
            requireActivity().getSharedPreferences("switch_prefs", AppCompatActivity.MODE_PRIVATE)
        val checkPrefs = prefs?.getBoolean("SWITCH_PREFS", false)

        if (checkPrefs == true) {
            englishPortugueseTranslator.translate(titleArgumentTranslate).addOnSuccessListener {
                titleView.text = it
            }.addOnFailureListener {
                titleView.text = titleArgumentTranslate
            }

            englishPortugueseTranslator.translate(textArgumentTranslate).addOnSuccessListener {
                textView.text = it
            }.addOnFailureListener {
                textView.text = textArgumentTranslate
            }
        } else {
            titleView.text = titleArgumentTranslate
            textView.text = textArgumentTranslate
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
}