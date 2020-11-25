package com.renan.digitalspace.exploration.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.renan.digitalspace.R

class ExploracaoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exploracao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        view.findViewById<MaterialButton>(R.id.btnSair).setOnClickListener {
            navController.navigate(R.id.action_exploracaoFragment_to_loginFragment)
        }
        view.findViewById<MaterialButton>(R.id.btnExplorar).setOnClickListener {
            navController.navigate(R.id.action_exploracaoFragment_to_viaLacteaFragment)
        }
        view.findViewById<MaterialButton>(R.id.btnFatoAstronomico).setOnClickListener {
            navController.navigate(R.id.action_exploracaoFragment_to_fatoAstronomicoFragment2)
        }
        view.findViewById<MaterialButton>(R.id.btnTerra).setOnClickListener {
            navController.navigate(R.id.action_exploracaoFragment_to_epicFragment)
        }
        view.findViewById<ImageButton>(R.id.btnBibliografia).setOnClickListener {
            navController.navigate(R.id.action_exploracaoFragment_to_bibliografiaFragment)

        }
    }
}

