package com.nasinha.digitalspace.apod.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.apod.model.ApodResponseModel
import com.nasinha.digitalspace.apod.repository.ApodRepository
import com.nasinha.digitalspace.apod.viewmodel.ApodViewModel
import com.nasinha.digitalspace.favorite.db.AppDatabase
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity
import com.nasinha.digitalspace.favorite.repository.FavoriteRepository
import com.nasinha.digitalspace.favorite.utils.FavoriteUtils
import com.nasinha.digitalspace.favorite.viewmodel.FavoriteViewModel
import com.nasinha.digitalspace.favorite.viewmodel.FavoriteViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class ApodFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _apodResponse: ApodResponseModel
    private lateinit var _favoriteViewModel: FavoriteViewModel

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
        return inflater.inflate(R.layout.fragment_apod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
                showLoading(false)
                showShareFavorite(false)

                e.message
                Picasso.get()
                    .load(R.drawable.gatinho)
                    .into(imgLoad)
                txtTitle.text = getString(R.string.apod_error)
                txtExplanation.text =
                    getString(R.string.apod_message)
            }

        })

        view.findViewById<ImageButton>(R.id.btnBackApod).setOnClickListener {
            activity?.onBackPressed()
        }

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
        val validation = arguments?.getString("VALIDATION")

        _apodResponse = it

        _favoriteViewModel.checkFavorite(it.url).observe(viewLifecycleOwner, {
            favoriteIsActive(it)
            btnFavorite()
        })

        if (validation == "isChecked") {
            englishPortugueseTranslator.translate(it.title).addOnSuccessListener {
                txtTitle.text = it
            }
                .addOnFailureListener {
                    txtTitle.text = _apodResponse.title
                }

            englishPortugueseTranslator.translate(it.explanation).addOnSuccessListener {

                txtExplanation.text = it + getText(R.string.quebra_linha)

            }.addOnFailureListener {
                txtExplanation.text = _apodResponse.explanation + getText(R.string.quebra_linha)
            }
            Picasso.get()
                .load(it.url)
                .into(imgLoad)
        } else {

            txtTitle.text = it.title
            txtExplanation.text = it.explanation + getText(R.string.quebra_linha)

            Picasso.get()
                .load(it.url)
                .into(imgLoad)
        }
        landScapeMode(it.url)

    }

    private fun favoriteIsActive(isChecked: Boolean) {
        val btnAddFavorite = _view.findViewById<CheckBox>(R.id.cbFavoriteApod)
        btnAddFavorite.isChecked = isChecked
    }

    private fun landScapeMode(urlImg: String) {
        val imgLoad = _view.findViewById<ImageView>(R.id.imgApod)
        val navController = NavHostFragment.findNavController(this)

        imgLoad.setOnClickListener {
            val bundle = bundleOf("IMGAPOD" to urlImg)
            navController.navigate(R.id.action_apodFragment_to_landsScapeApodFragment, bundle)
        }

    }


    private fun btnFavorite() {
        val btnAddFavorite = _view.findViewById<CheckBox>(R.id.cbFavoriteApod)
        btnAddFavorite.setOnCheckedChangeListener { _, isChecked ->
            val favorite = FavoriteEntity(
                id = 0,
                image = _apodResponse.url,
                title = _apodResponse.title,
                text = _apodResponse.explanation,
                date = _apodResponse.date,
                active = true
            )
            if (isChecked) {
                _favoriteViewModel.addFavorite(favorite).observe(viewLifecycleOwner, { })
            } else {
                _favoriteViewModel.deleteFavoriteItem(favorite.image)
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
            shareHandler()
        }
    }

    private fun shareHandler() {
        lifecycleScope.launch {
            val imageBitmap = FavoriteUtils.getBitmapFromView(_view, _apodResponse.url)
            activity?.let { FavoriteUtils.checkPermissions(it, _view, imageBitmap) }
        }
    }
}