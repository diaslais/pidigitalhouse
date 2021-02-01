package com.nasinha.digitalspace.space.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.utils.DrawerUtils.lockDrawer

class GalaxyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_galaxy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lockDrawer(requireActivity())

        val navController = findNavController()

        view.findViewById<MaterialButton>(R.id.btnConhecaSistemaSolar).setOnClickListener {
            navController.navigate(R.id.action_galaxyFragment_to_solarSystemFragment)
        }
        view.findViewById<ImageButton>(R.id.btnBackMilkyWay).setOnClickListener {
            activity?.onBackPressed()
        }
    }
}