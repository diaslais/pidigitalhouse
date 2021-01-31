package com.nasinha.digitalspace.settings.view

import android.app.AlertDialog
import android.app.DownloadManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.profile.viewmodel.ProfileViewModel
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.Constants.APP_KEY
import com.nasinha.digitalspace.utils.Constants.FACEBOOKCOM
import com.nasinha.digitalspace.utils.Constants.GOOGLECOM
import com.nasinha.digitalspace.utils.Constants.PASSWORD
import com.nasinha.digitalspace.utils.Constants.SWITCH_PREFS
import com.nasinha.digitalspace.utils.TranslateUtils.conditions
import com.nasinha.digitalspace.utils.TranslateUtils.getCheckPrefs
import com.nasinha.digitalspace.utils.TranslateUtils.options
import com.nasinha.digitalspace.utils.TranslateUtils.saveCheckPrefs


class SettingsFragment : Fragment() {

    private lateinit var _view: View
    private val _profileViewModel: ProfileViewModel by lazy {
        ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view
        closeBtn()
        initViewModel()
        deleteAccountHandler()
        switchTranslateListener()

    }

    private fun switchTranslateListener() {
        val switchButton = _view.findViewById<SwitchCompat>(R.id.btnSwitchTranslate)

        switchButton.isChecked = getCheckPrefs(_view.context)

        switchButton.setOnCheckedChangeListener { _, isChecked ->

            saveCheckPrefs(_view.context, isChecked)

            if (isChecked) {
                val alertDialog = AlertDialog.Builder(_view.context)

                alertDialog.setTitle(getString(R.string.alerta_traducao))
                alertDialog.setMessage(getString(R.string.message_traducao))
                alertDialog.setPositiveButton(getString(R.string.sim)) { dialog, _ ->
                    translateDownload()
                    showLoading(true)
                    dialog.dismiss()
                }
                alertDialog.setNegativeButton(getString(R.string.nao)) { dialog, _ ->
                    switchButton.isChecked = false
                    dialog.dismiss()
                }
                alertDialog.show()

            } else {
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        val progressBar = _view.findViewById<LinearLayout>(R.id.llProgressSettings)
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun translateDownload() {
        val englishPortugueseTranslator = Translation.getClient(options())

        englishPortugueseTranslator.downloadModelIfNeeded(conditions())
            .addOnSuccessListener {

                showLoading(false)
                snackBarMessage(getString(R.string.message_install))
                englishPortugueseTranslator.close()

            }
            .addOnFailureListener {

                showLoading(false)
                snackBarMessage(getString(R.string.failed_download))

            }

    }

    private fun initViewModel() {
        _profileViewModel.error.observe(viewLifecycleOwner, { e ->
            snackBarMessage(e)
        })

        _profileViewModel.stateDelete.observe(viewLifecycleOwner, {
            if (it) {
                snackBarMessage(getString(R.string.conta_excluida))
                val navController = findNavController()
                if (AuthUtil.getUserProvider(requireActivity()) != PASSWORD) {
                    AuthUtil.clearUserInfo(requireActivity())
                    navController.navigate(R.id.action_settingsFragment_to_loginFragment)
                }
            }
        })
    }

    private fun snackBarMessage(message: String) {
        Snackbar.make(_view, message, Snackbar.LENGTH_LONG).show()
    }

    private fun closeBtn() {
        val btnClose = _view.findViewById<ImageButton>(R.id.ibBackSettings)
        btnClose.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }


    private fun deleteAccountHandler() {
        val deleteBtn = _view.findViewById<MaterialButton>(R.id.mbDeleteAccountSettings)
        deleteBtn.setOnClickListener {
            deleteBtn.isEnabled = false

            Handler(Looper.getMainLooper()).postDelayed({
                deleteBtn.isEnabled = true
            }, 1000)

            val provider = AuthUtil.getUserProvider(requireActivity())

            when (provider) {
                PASSWORD -> {
                    val navController = findNavController()
                    navController.navigate(R.id.action_settingsFragment_to_bottomsheetPasswordFragment)
                }
                FACEBOOKCOM -> {
                    confirmDialog()
                }
                GOOGLECOM -> {
                    confirmDialog()
                }
                else -> {
                    snackBarMessage(getString(R.string.faca_login_novamente))
                }
            }
        }
    }

    private fun confirmDialog() {
        val alertDialog = AlertDialog.Builder(_view.context)
        alertDialog.setTitle(getString(R.string.excluir_conta))
        alertDialog.setMessage(getString(R.string.voce_quer_mesmo_conta))
        alertDialog.setPositiveButton(getString(R.string.sim)) { _, _ ->
            _profileViewModel.getUserCredential(_view, null, null)
        }
        alertDialog.setNegativeButton(getString(R.string.nao)) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }
}