package com.nasinha.digitalspace.exploration.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.apod.model.ApodResponseModel
import com.nasinha.digitalspace.apod.repository.ApodRepository
import com.nasinha.digitalspace.apod.viewmodel.ApodViewModel
import com.nasinha.digitalspace.authentication.AppUtil
import com.nasinha.digitalspace.exploration.utils.DrawerUtils.unlockDrawer

class ExplorationFragment : Fragment() {
     var mediaType: String = ""
    lateinit var urlVideo: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exploration, container, false)
    }

    @SuppressLint("RtlHardcoded")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isFirstTime()) {
            welcomeDialog(view)
        }

        val viewModel = ViewModelProvider(
            this,
            ApodViewModel.ApodViewModelFactory(ApodRepository())
        ).get(ApodViewModel::class.java)

        viewModel.getDataApod().observe(viewLifecycleOwner) {
            mediaType(it as ApodResponseModel)
        }

        navInfoHeader()
        unlockDrawer(requireActivity())
        drawerListener(view)
    }

    private fun welcomeDialog(explorationView: View) {
        val dialog = AlertDialog.Builder(explorationView.context)
        val view: View =
            requireActivity().layoutInflater.inflate(R.layout.welcome_alert, null)
        dialog.setView(view)

        val btnLetsGo = view.findViewById<Button>(R.id.btnLetsGo)
        val closeWelcomeDialog = view.findViewById<ImageButton>(R.id.ibCloseWelcome)

        val alert = dialog.create()

        closeWelcomeDialog.setOnClickListener {
            alert.dismiss()
        }
        btnLetsGo.setOnClickListener {
            alert.dismiss()
        }

        alert.setCancelable(false)
        alert.show()

        alert?.setOnKeyListener { dialog, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog?.dismiss()
                true
            }
            false
        }
    }

    private fun isFirstTime(): Boolean {
        val prefs = activity?.getSharedPreferences(PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
        val dialogShown = prefs?.getBoolean(DIALOG_SHOWN, true)

        if (dialogShown!!) {
            prefs.edit()?.putBoolean(DIALOG_SHOWN, false)?.apply()
        }
        return dialogShown
    }

    private fun mediaType(it: ApodResponseModel) {
        mediaType = it.media_type
        urlVideo = it.url
    }

    private fun drawerListener(view: View) {
        val navController = findNavController()

        val explorationToolBar = view.findViewById<MaterialToolbar>(R.id.explorationTopAppBar)

        val drawerLayout = requireActivity().findViewById<DrawerLayout>(R.id.drawer_layout)

        explorationToolBar.setNavigationOnClickListener {
            drawerLayout?.openDrawer(Gravity.LEFT)
        }
//        Explorer
        view.findViewById<MaterialCardView>(R.id.cardExplore).setOnClickListener {
            navController.navigate(R.id.action_explorationFragment_to_galaxyFragment)
        }
//        APOD
        view.findViewById<MaterialCardView>(R.id.cardAstronomicalFact).setOnClickListener {
            Log.d("RENANN","$mediaType")
            if (mediaType == "video") {
                navController.navigate(R.id.action_explorationFragment_to_apodVideoActivity)
            } else {
                navController.navigate(R.id.action_explorationFragment_to_apodFragment)
            }

        }
//        EPIC
        view.findViewById<MaterialCardView>(R.id.cardEarth).setOnClickListener {
            navController.navigate(R.id.action_explorationFragment_to_epicFragment)
        }
//        FAVORITES
        view.findViewById<MaterialButton>(R.id.btnFavorites).setOnClickListener {
            navController.navigate(R.id.action_explorationFragment_to_favoriteFragment)
        }
//        QUIZ
        view.findViewById<MaterialCardView>(R.id.cardQuiz).setOnClickListener {
            navController.navigate(R.id.action_explorationFragment_to_quizFragment)
        }
//        BIBLIOGRAPHY
        view.findViewById<MaterialButton>(R.id.btnBibliography).setOnClickListener {
            navController.navigate(R.id.action_explorationFragment_to_bibliographyFragment)
        }
    }

    @SuppressLint("DefaultLocale")
    private fun navInfoHeader() {
        val navigationView = activity?.findViewById<NavigationView>(R.id.navigationView)
        val headerView = navigationView!!.getHeaderView(0)
        val nameDrawer = headerView.findViewById<TextView>(R.id.tvNameNavHeader)
        val nameShared = AppUtil.getUserName(requireActivity())
        nameDrawer.text = nameShared
    }

    companion object {
        const val PREFS_NAME = "welcome_prefs"
        const val DIALOG_SHOWN = "dialog_shown"
    }
}

