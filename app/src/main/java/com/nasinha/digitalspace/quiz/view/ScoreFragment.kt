package com.nasinha.digitalspace.quiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.quiz.db.QuizDatabase
import com.nasinha.digitalspace.quiz.repository.QuizRepository
import com.nasinha.digitalspace.quiz.viewmodel.QuizViewModel
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.DrawerUtils

class ScoreFragment : Fragment() {
    private lateinit var _scoreView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _scoreView = inflater.inflate(R.layout.fragment_score, container, false)
        return _scoreView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DrawerUtils.lockDrawer(requireActivity())

        val btnBack = view.findViewById<ImageButton>(R.id.btnBackScore)

        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

        val viewModel = ViewModelProvider(
            this,
            QuizViewModel.QuizViewModelFactory(
                QuizRepository(
                    QuizDatabase.getDatabase(view.context).quizDao()
                )
            )
        ).get(QuizViewModel::class.java)

        val positions = arrayListOf<String>("#1", "#2", "#3", "#4", "#5")

        val trophies = arrayListOf<Int>(
            R.drawable.ic_baseline_emoji_events_24_gold,
            R.drawable.ic_baseline_emoji_events_24_silver,
            R.drawable.ic_baseline_emoji_events_24_bronze
        )

        val userId = AuthUtil.getUserId(requireActivity().application)!!

    }


}