package com.nasinha.digitalspace.quiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nasinha.digitalspace.R


class QuizScoreFragment : Fragment() {

    private lateinit var _view: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _view = inflater.inflate(R.layout.fragment_quiz_score, container, false)
        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view

        val totalQuestions = requireArguments().getInt("TOTAL_QUESTIONS")
        val correctAnswers = requireArguments().getInt("CORRECT_ANSWERS")
        val result = _view.findViewById<TextView>(R.id.txtScore)
        result.text = "$correctAnswers / $totalQuestions"


        val navController = findNavController()

        view.findViewById<ImageButton>(R.id.btnExitToExploration).setOnClickListener {
            navController.navigate(R.id.action_quizScoreFragment_to_explorationFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(this){
            navController.navigate(R.id.action_quizScoreFragment_to_quizFragment)
        }
    }

    

}
