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

class ViaLacteaFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_via_lactea, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        view.findViewById<MaterialButton>(R.id.btnConhecaSistemaSolar).setOnClickListener {
            navController.navigate(R.id.action_viaLacteaFragment_to_sistemaSolarFragment)
        }
        view.findViewById<ImageButton>(R.id.btnBackMilkyWay).setOnClickListener {
            navController.navigate(R.id.action_viaLacteaFragment_to_exploracaoFragment)
        }
    }
}