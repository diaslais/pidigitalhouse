package com.nasinha.digitalspace.apod.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.nasinha.digitalspace.R
import com.squareup.picasso.Picasso


class LandsScapeApodFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lands_scape_apod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        closeland()
        showImg()
    }

    private fun showImg() {
        val receiveImg = arguments?.getString("IMGAPOD")
        val imgLandScape = view?.findViewById<ImageView>(R.id.imageViewLandScape)

        Picasso.get()
            .load(receiveImg)
            .resize(1000,1000)
            .into(imgLandScape)
    }

    private fun closeland() {

        view?.findViewById<ImageButton>(R.id.imageButtonCloseLand)?.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}
