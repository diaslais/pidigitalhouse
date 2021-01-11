package com.nasinha.digitalspace.apod.view

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.youtube.player.*
import com.nasinha.digitalspace.R
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
        text = it.explanation
        title = it.title
        url = it.url
    }

    private fun getIdVideo(urlVideo: String): String {
        val index = urlVideo.indexOf("=")
        val url = urlVideo.subSequence(index + 1, url.length)
        val idVideo = url.subSequence(0, 11)
        return idVideo.toString()
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1?.cueVideo(getIdVideo(url))

    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        TODO("Not yet implemented")
    }
}