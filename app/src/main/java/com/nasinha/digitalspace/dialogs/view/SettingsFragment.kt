package com.nasinha.digitalspace.dialogs.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.utils.Constants.APP_KEY
import com.nasinha.digitalspace.utils.Constants.SWITCH_PREFS


class SettingsFragment : DialogFragment() {

    private lateinit var _view: View

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

    private fun confirmButton() {
        val confirmButtonSetting = _view.findViewById<MaterialButton>(R.id.confirmButtonSettings)
        confirmButtonSetting.setOnClickListener {
            dismiss()
        }
    }

    private fun closeBtn() {
        val btnClose = _view.findViewById<ImageButton>(R.id.ibCloseDialogNameSettings)
        btnClose.setOnClickListener {
            dismiss()
        }
    }


}