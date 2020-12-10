package com.renan.digitalspace.apod.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.renan.digitalspace.R
import com.renan.digitalspace.apod.model.ApodResponseModel
import com.renan.digitalspace.apod.repository.ApodRepository
import com.renan.digitalspace.apod.viewmodel.ApodViewModel
import com.renan.digitalspace.favorite.db.AppDatabase
import com.renan.digitalspace.favorite.entity.FavoriteEntity
import com.renan.digitalspace.favorite.repository.FavoriteRepository
import com.renan.digitalspace.favorite.viewmodel.FavoriteViewModel
import com.renan.digitalspace.favorite.viewmodel.FavoriteViewModelFactory
import com.squareup.picasso.Picasso
import java.util.EnumSet.of
import java.util.List.of
import java.util.Map.of
import java.util.OptionalInt.of


class ApodFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _apodResponse: ApodResponseModel
    private val _favoriteViewModel by activityViewModels<FavoriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fato_astronomico, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        val viewModel = ViewModelProvider(
            this, ApodViewModel.ApodViewModelFactory(
                ApodRepository()
            )
        ).get(ApodViewModel::class.java)

        viewModel.getDataApod().observe(viewLifecycleOwner, {

            mostrarResultados(it, view)

        })

        val navController = findNavController()

        view.findViewById<ImageButton>(R.id.btnBackApod).setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun mostrarResultados(it: ApodResponseModel, view: View) {
        val imgLoad = view.findViewById<ImageView>(R.id.imgApod)
        val txtExplanation = view.findViewById<TextView>(R.id.txtExplanationApod)
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val string = ""
        _apodResponse = it
        btnFavorite()
        try {

            if (it.explanation.isNotEmpty()) {
                txtTitle.text = it.title
                txtExplanation.text = it.explanation

                Picasso.get()
                    .load(it.url)
                    .into(imgLoad)

            } else {
                throw RuntimeException()
            }


        } catch (exception: RuntimeException) {
            Picasso.get()
                .load(R.drawable.gatinho)
                .into(imgLoad)
            txtTitle.text = getString(R.string.apod_error)
            txtExplanation.text =
                getString(R.string.apod_message)
        }

    }


    private fun btnFavorite() {
        val btnAddFavorite = _view.findViewById<CheckBox>(R.id.ibFavoriteButtonFato)
        btnAddFavorite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val favorite = FavoriteEntity(
                    0,
                    _apodResponse.url,
                    _apodResponse.title,
                    _apodResponse.date,
                    true
                )
                _favoriteViewModel.addFavorite(favorite)
            }
        }
    }

}