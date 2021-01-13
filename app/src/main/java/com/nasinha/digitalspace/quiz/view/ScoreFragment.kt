package com.nasinha.digitalspace.quiz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.nasinha.digitalspace.R

class ScoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val totalQuestions = requireArguments().getInt("TOTAL_QUESTIONS")
        val correctAnswers = requireArguments().getInt("CORRECT_ANSWERS")
        val imageAstronaut = requireArguments().getInt("ASTRONAUT_IMAGE")
        val messageResult = requireArguments().getString("RESULTS_MESSAGE")

        val txtResults = view.findViewById<TextView>(R.id.txtResults)
        val imgAstronautResults = view.findViewById<ImageView>(R.id.imgAstronautResults)
        val txtScore = view.findViewById<TextView>(R.id.txtScore)
        val btnPlayAgain = view.findViewById<Button>(R.id.btnPlayAgain)
        val btnBackToHome = view.findViewById<ImageButton>(R.id.btnBackScore)
        val navController = findNavController()

        txtResults.text = messageResult
        imgAstronautResults.setImageResource(imageAstronaut)
        txtScore.text = getString(R.string.contagem_questoes, correctAnswers, totalQuestions)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            navController.popBackStack()
            navController.popBackStack()
        }
        callback.isEnabled = true

        btnPlayAgain.setOnClickListener{
            activity?.onBackPressed()
        }
        btnBackToHome.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}