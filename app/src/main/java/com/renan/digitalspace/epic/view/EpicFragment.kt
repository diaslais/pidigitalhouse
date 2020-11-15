package com.renan.digitalspace.epic.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.renan.digitalspace.R
import com.renan.digitalspace.epic.NetworkUtilsEpic
import com.renan.digitalspace.epic.model.ApiResponseModelEPIC
import com.renan.digitalspace.epic.repository.EndPointEpic
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpicFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_epic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val picasso = Picasso.get()
        val imgEpic = view.findViewById<ImageView>(R.id.imgEpic)

        val remote =
            NetworkUtilsEpic.createService(EndPointEpic::class.java)
        val call: Call<List<ApiResponseModelEPIC>> = remote.getImageDay()

        val response = call.enqueue(object : Callback<List<ApiResponseModelEPIC>>{
            override fun onResponse(
                call: Call<List<ApiResponseModelEPIC>>,
                response: Response<List<ApiResponseModelEPIC>>
            ) {
                val s = response.body()
            }

            override fun onFailure(call: Call<List<ApiResponseModelEPIC>>, t: Throwable) {
                val s = t.message
            }


        })


    }
}