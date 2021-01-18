package com.nasinha.digitalspace.apod.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.youtube.player.*
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.utils.ApodUtils.getIdVideo
import com.nasinha.digitalspace.apod.model.ApodResponseModel
import com.nasinha.digitalspace.apod.repository.ApodRepository
import com.nasinha.digitalspace.apod.viewmodel.ApodViewModel

class ApodVideoActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener {
    lateinit var text: String
    lateinit var title: String
    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apod_video)

        val viewModel = ViewModelProvider(
            this, ApodViewModel.ApodViewModelFactory(
                ApodRepository()
            )
        ).get(ApodViewModel::class.java)

        viewModel.getDataApod().observe(this, {
            showResults(it as ApodResponseModel)
        })

        val youtubePlayer =
            supportFragmentManager.findFragmentById(R.id.youtube_view) as YouTubePlayerSupportFragment?
        youtubePlayer?.initialize("AIzaSyCBxR_q7U_L1xSdppQ0LG-0Vwq4sxANHHE", this)
    }

    private fun showResults(it: ApodResponseModel) {
        val txtExplanation = findViewById<TextView>(R.id.txtExplanationApod)
        val txtTitle = findViewById<TextView>(R.id.txtTitle)
        txtExplanation.text = it.explanation
        txtTitle.text = it.title
        url = getIdVideo(it.url)

    }


    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1?.cueVideo(url)
        //Log.d("RENAN", url)

    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        TODO("Not yet implemented")
    }
}