package com.nasinha.digitalspace.apod.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.apod.model.ApodResponseModel
import com.nasinha.digitalspace.apod.repository.ApodRepository
import com.nasinha.digitalspace.apod.viewmodel.ApodViewModel


class ApodFragmentVideo : Fragment() {

    lateinit var _view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apod_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        val viewModel = ViewModelProvider(
            this,
            ApodViewModel.ApodViewModelFactory(ApodRepository())
        ).get(ApodViewModel::class.java)

        viewModel.getDataApod().observe(viewLifecycleOwner,{
            showResults(it as ApodResponseModel)
        })



    }

    private fun showResults(it: ApodResponseModel) {

        showPlayerVideo(it.url)
    }

    private fun showPlayerVideo(url: String) {

        val youtubePlayer = _view.findViewById<YouTubePlayerView>(R.id.youtube_view)
        youtubePlayer.initialize(
            "AIzaSyCBxR_q7U_L1xSdppQ0LG-0Vwq4sxANHHE",
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    p1?.loadVideo(url)
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    TODO("Not yet implemented")
                }

            })
    }


}