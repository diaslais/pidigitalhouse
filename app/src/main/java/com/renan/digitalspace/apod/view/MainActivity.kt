package com.renan.digitalspace.apod.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.renan.digitalspace.NetworkUtils
import com.renan.digitalspace.R
import com.renan.digitalspace.apod.model.ApiResponseModelAPOD
import com.renan.digitalspace.apod.repository.EndPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val remote = NetworkUtils.createService(EndPoint::class.java)
        val call: Call<ApiResponseModelAPOD> = remote.getAstronomicalFact()

        val response = call.enqueue(object : Callback<ApiResponseModelAPOD>{
            override fun onResponse(
                call: Call<ApiResponseModelAPOD>,
                response: Response<ApiResponseModelAPOD>
            ) {
                val s = response.body()
            }

            override fun onFailure(call: Call<ApiResponseModelAPOD>, t: Throwable) {

                val s = t.message
            }

        })

    }
}