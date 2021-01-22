package com.nasinha.digitalspace.quiz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.quiz.db.QuizDatabase
import com.nasinha.digitalspace.quiz.repository.QuizRepository
import com.nasinha.digitalspace.quiz.viewmodel.QuizViewModel

class ScoreFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<ImageButton>(R.id.btnBackScore)

        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

        val viewModel = ViewModelProvider(
            this,
            QuizViewModel.QuizViewModelFactory(QuizRepository(QuizDatabase.getDatabase(view.context).quizDao()))
        ).get(QuizViewModel::class.java)

        val id = view.findViewById<TextView>(R.id.testeId)
        val date = view.findViewById<TextView>(R.id.testeDate)
        val points = view.findViewById<TextView>(R.id.testePoints)

        viewModel.scoreList.observe(viewLifecycleOwner) {
            it.forEach {
                id.text = it.id.toString()
                date.text = it.date
                points.text = it.points.toString()
            }
        }
    }
}