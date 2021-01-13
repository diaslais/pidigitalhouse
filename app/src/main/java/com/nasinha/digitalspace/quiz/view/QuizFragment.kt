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
import com.nasinha.digitalspace.exploration.utils.DrawerUtils.lockDrawer

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
        lockDrawer(requireActivity())

        view.findViewById<ImageButton>(R.id.btnBackQuiz).setOnClickListener {
            activity?.onBackPressed()
        }

        val navController = findNavController()

        view.findViewById<MaterialButton>(R.id.btnPlay).setOnClickListener {
            navController.navigate(R.id.action_quizFragment_to_questionsFragment)
        }
    }
}
