package com.nasinha.digitalspace.quiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.nasinha.digitalspace.R

class QuizQuestionsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_questions, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.btnBackQuestions).setOnClickListener {
            activity?.onBackPressed()
        }

        val navController = findNavController()

        view.findViewById<MaterialButton>(R.id.btnNextQuestion).setOnClickListener {
            navController.navigate(R.id.action_quizQuestionsFragment_to_quizScoreFragment  )
        }
    }
}

