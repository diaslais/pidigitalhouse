package com.nasinha.digitalspace.settings.view

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
import com.nasinha.digitalspace.settings.viewmodel.SettingsViewModel
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.Constants.APP_KEY
import com.nasinha.digitalspace.utils.Constants.FACEBOOKCOM
import com.nasinha.digitalspace.utils.Constants.GOOGLECOM
import com.nasinha.digitalspace.utils.Constants.PASSWORD
import com.nasinha.digitalspace.utils.Constants.SWITCH_PREFS


class SettingsFragment : Fragment() {

    private lateinit var _view: View
    private val _settingsViewModel: SettingsViewModel by lazy {
        ViewModelProvider(this).get(SettingsViewModel::class.java)
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

        val switchButton = _view.findViewById<SwitchCompat>(R.id.btnSwitchTranslate)

        val prefs = activity?.getSharedPreferences(APP_KEY, AppCompatActivity.MODE_PRIVATE)

        val prefsChecked = prefs?.getBoolean(SWITCH_PREFS, false)

        if (prefsChecked != null) {
            switchButton.isChecked = prefsChecked
        }

        closeBtn()
        confirmButton()
        initViewModel()
        deleteAccountHandler()

        switchButton.setOnCheckedChangeListener { _, isChecked ->

            prefs?.edit()?.putBoolean(SWITCH_PREFS, isChecked)?.apply()

            if (isChecked) {
                val options = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(TranslateLanguage.PORTUGUESE)
                    .build()

                val englishPortugueseTranslator = Translation.getClient(options)
                lifecycle.addObserver(englishPortugueseTranslator)

                val conditions = DownloadConditions.Builder()
                    .requireWifi()
                    .build()

                englishPortugueseTranslator.downloadModelIfNeeded(conditions)
                    .addOnSuccessListener {
                        // Model downloaded successfully. Okay to start translating.
                        // (Set a flag, unhide the translation UI, etc.)

                    }
                    .addOnFailureListener { _ ->
                        // Model couldn’t be downloaded or other internal error.
                        // ...
                    }

                val alertDialog = AlertDialog.Builder(_view.context)

                alertDialog.setTitle(getString(R.string.alerta_traducao))
                alertDialog.setMessage(getString(R.string.message_traducao))
                alertDialog.setPositiveButton(getString(R.string.sim)) { dialog, _ ->
                    dialog.dismiss()

                }
                alertDialog.setNegativeButton(getString(R.string.nao)) { dialog, _ ->
                    switchButton.isChecked = false
                    dialog.dismiss()
                }
                alertDialog.show()


            }
        }

    }

    private fun initViewModel() {
        _settingsViewModel.error.observe(viewLifecycleOwner, { e ->
            snackBarMessage(e)
        })
    }

    private fun snackBarMessage(message: String) {
        Snackbar.make(_view, message, Snackbar.LENGTH_LONG).show()
    }

    private fun confirmButton() {
        val confirmButtonSetting = _view.findViewById<MaterialButton>(R.id.confirmButtonSettings)
        confirmButtonSetting.setOnClickListener {
            requireActivity().onBackPressed()
        }
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
                }
                GOOGLECOM -> {
                }
            }
        }
    }
}