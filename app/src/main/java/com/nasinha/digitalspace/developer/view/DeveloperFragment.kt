package com.nasinha.digitalspace.developer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nasinha.digitalspace.R

class DeveloperFragment : Fragment() {
    private lateinit var _view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_developer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view
        backBtn()
    }

    private fun backBtn() {
        val returnBtn = _view.findViewById<ImageButton>(R.id.ibBackDeveloper)
        returnBtn.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_developerFragment_to_explorationFragment)
        }
    }
}