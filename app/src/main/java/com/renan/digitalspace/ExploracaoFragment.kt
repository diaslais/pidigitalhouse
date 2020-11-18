package com.renan.digitalspace

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton

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
            navController.navigate(R.id.actio)

            view.findViewById<MaterialButton>(R.id.btnExplorar).setOnClickListener {
                navController.navigate(R.id.actio)

                view.findViewById<MaterialButton>(R.id.btnFatoAstronomico).setOnClickListener {
                    navController.navigate(R.id.actio)

                    view.findViewById<MaterialButton>(R.id.btnTerra).setOnClickListener {
                        navController.navigate(R.id.actio)

                        view.findViewById<Button>(R.id.btnBibliografia
                        ).setOnClickListener {
                            navController.navigate(R.id.actio)

            }
        }
    }
