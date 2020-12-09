package com.renan.digitalspace.quiz.view

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.renan.digitalspace.R


class QuizFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        view.findViewById<MaterialButton>(R.id.btnPlay).setOnClickListener {
            navController.navigate(R.id.action_quizFragment_to_quizQuestionsFragment)
        }

        view.findViewById<ImageButton>(R.id.btnBackQuiz).setOnClickListener {
            activity?.onBackPressed()
        }

    }
}