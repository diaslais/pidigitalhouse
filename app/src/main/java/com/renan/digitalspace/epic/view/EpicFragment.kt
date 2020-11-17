package com.renan.digitalspace.epic.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.renan.digitalspace.R
import com.renan.digitalspace.epic.NetworkUtilsEpic
import com.renan.digitalspace.epic.model.ApiResponseModelEPIC
import com.renan.digitalspace.epic.repository.EndPointEpic
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EpicFragment : Fragment() {
    lateinit var imageId: String

    lateinit var yearId: String
    lateinit var monthId: String
    lateinit var dayId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_epic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val remote =
            NetworkUtilsEpic.createService(EndPointEpic::class.java)
        val call: Call<List<ApiResponseModelEPIC>> = remote.getImageDay()

        val response = call.enqueue(object : Callback<List<ApiResponseModelEPIC>> {
            override fun onResponse(
                call: Call<List<ApiResponseModelEPIC>>,
                response: Response<List<ApiResponseModelEPIC>>
            ) {
                imageId = response.body()?.get(0)?.image.toString() + ".png"

                responseDate()
            }

            override fun onFailure(call: Call<List<ApiResponseModelEPIC>>, t: Throwable) {
                val s = t.message
            }


        })


    }

    private fun responseDate() {
        val remoteDate = NetworkUtilsEpic.createService(EndPointEpic::class.java)
        val callDate: Call<List<String>> = remoteDate.getLastDAy()

        val responseDate = callDate.enqueue(object : Callback<List<String>> {

            override fun onResponse(
                call: Call<List<String>>,
                responseDate: Response<List<String>>
            ) {
                val respost = responseDate.body()
                val respostSpecific = respost?.get(index = respost.size - 1)
                // dateId = respostSpecific?.replace("-", "/").toString()
                val dateList = respostSpecific?.split('-')
                yearId = dateList?.get(0).toString()
                monthId = dateList?.get(1).toString()
                dayId = dateList?.get(2).toString()

                val imgEpic = view?.findViewById<ImageView>(R.id.imgEpic)
                val picasso = Picasso.get()
                picasso.load("https://epic.gsfc.nasa.gov/archive/natural/${yearId}/${monthId}/${dayId}/png/${imageId}")
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                val s = t.message
            }

        })
    }

}