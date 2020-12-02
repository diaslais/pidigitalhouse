package com.renan.digitalspace.exploration.view

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

class ExploracaoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exploracao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        view.findViewById<MaterialButton>(R.id.ibNavigationExploracao).setOnClickListener {
            val drawerLayout = activity?.findViewById<DrawerLayout>(R.id.drawer_layout)
            drawerLayout?.openDrawer(Gravity.LEFT)
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
        view.findViewById<ImageButton>(R.id.btnBibliography).setOnClickListener {
            navController.navigate(R.id.action_exploracaoFragment_to_bibliografiaFragment)

        }
    }
}

