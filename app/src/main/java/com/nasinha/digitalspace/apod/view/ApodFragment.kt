package com.nasinha.digitalspace.apod.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.apod.model.ApodResponseModel
import com.nasinha.digitalspace.apod.repository.ApodRepository
import com.nasinha.digitalspace.apod.viewmodel.ApodViewModel
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity
import com.squareup.picasso.Picasso
import java.io.IOException


class ApodFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _apodResponse: ApodResponseModel


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

        viewModel.getDataApod().observe(viewLifecycleOwner, {

            try {
                mostrarResultados(it as ApodResponseModel, view)
            } catch (e: Exception) {
                e.message
                Picasso.get()
                    .load(R.drawable.gatinho)
                    .into(imgLoad)
                txtTitle.text = getString(R.string.apod_error)
                txtExplanation.text =
                    getString(R.string.apod_message)
            }


        })

        val navController = findNavController()

        view.findViewById<ImageButton>(R.id.btnBackApod).setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun mostrarResultados(it: ApodResponseModel, view: View) {
        val imgLoad = _view.findViewById<ImageView>(R.id.imgApod)
        val txtExplanation = _view.findViewById<TextView>(R.id.txtExplanationApod)
        val txtTitle = _view.findViewById<TextView>(R.id.txtTitle)

        _apodResponse = it
        btnFavorite()

        txtTitle.text = it.title
        txtExplanation.text = it.explanation + getText(R.string.quebra_linha)

        Picasso.get()
            .load(it.url)
            .into(imgLoad)

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

            }
        }
    }

}