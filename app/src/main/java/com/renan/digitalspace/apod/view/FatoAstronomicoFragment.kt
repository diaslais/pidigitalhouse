package com.renan.digitalspace.apod.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.renan.digitalspace.apod.data.NetworkUtilsApod
import com.renan.digitalspace.R
import com.renan.digitalspace.apod.model.ApiResponseModelAPOD
import com.renan.digitalspace.apod.repository.EndPointApod
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FatoAstronomicoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fato_astronomico, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val picasso = Picasso.get()

        val imgload = view.findViewById<ImageView>(R.id.imgApod)
        val txtExplanation =  view.findViewById<TextView>(R.id.txtExplanationApod)
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)


        val remote = NetworkUtilsApod.createService(EndPointApod::class.java)
        val call: Call<ApiResponseModelAPOD> = remote.getAstronomicalFact()

        val response = call.enqueue(object : Callback<ApiResponseModelAPOD> {
            override fun onResponse(
                call: Call<ApiResponseModelAPOD>,
                response: Response<ApiResponseModelAPOD>
            ) {

                picasso.load(response.body()?.url).into(imgload)
                txtTitle.text = response.body()?.title
                txtExplanation.text = response.body()?.explanation

            }

            override fun onFailure(call: Call<ApiResponseModelAPOD>, t: Throwable) {

                val s = t.message
            }

        })
    }
}