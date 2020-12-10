package com.renan.digitalspace.exploration.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.renan.digitalspace.R

class ExplorationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exploration, container, false)
    }

    @SuppressLint("RtlHardcoded")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        val explorationToolBar = view.findViewById<MaterialToolbar>(R.id.explorationTopAppBar)

        explorationToolBar.setNavigationOnClickListener {
            val drawerLayout = activity?.findViewById<DrawerLayout>(R.id.drawer_layout)
            drawerLayout?.openDrawer(Gravity.LEFT)
        }
        view.findViewById<MaterialCardView>(R.id.cardExplore).setOnClickListener {
            navController.navigate(R.id.action_explorationFragment_to_viaLacteaFragment)
        }
        view.findViewById<MaterialCardView>(R.id.cardAstronomicalFact).setOnClickListener {
            navController.navigate(R.id.action_explorationFragment_to_fatoAstronomicoFragment2)
        }
        view.findViewById<MaterialCardView>(R.id.cardEarth).setOnClickListener {
            navController.navigate(R.id.action_explorationFragment_to_epicFragment)
        }
        view.findViewById<MaterialButton>(R.id.btnFavorites).setOnClickListener {
            navController.navigate(R.id.action_explorationFragment_to_favoriteFragment)
        }
        view.findViewById<MaterialButton>(R.id.btnQuiz).setOnClickListener {
            navController.navigate(R.id.action_explorationFragment_to_quizFragment)
        }
        view.findViewById<MaterialButton>(R.id.btnBibliography).setOnClickListener {
            navController.navigate(R.id.action_explorationFragment_to_bibliografiaFragment)
        }

    }
}

