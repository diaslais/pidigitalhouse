package com.renan.digitalspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.renan.digitalspace.apod.NetworkUtilsApod
import com.renan.digitalspace.apod.model.ApiResponseModelAPOD
import com.renan.digitalspace.apod.repository.EndPointApod
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val remote = NetworkUtilsApod.createService(EndPointApod::class.java)
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