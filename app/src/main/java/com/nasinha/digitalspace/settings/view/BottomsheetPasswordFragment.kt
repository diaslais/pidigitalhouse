package com.nasinha.digitalspace.settings.view

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.nasinha.digitalspace.MainActivity
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.settings.viewmodel.SettingsViewModel
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.AuthUtil.hideKeyboard
import kotlinx.android.synthetic.main.fragment_signup.*

class BottomsheetPasswordFragment : BottomSheetDialogFragment() {

    private lateinit var _view: View
    private val _settingsViewModel: SettingsViewModel by lazy {
        ViewModelProvider(this).get(SettingsViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottomsheet_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        initViewModel()
        confirmListener()
    }

    private fun initViewModel() {
        _settingsViewModel.error.observe(viewLifecycleOwner, { s ->
            snackBarMessage(s)
        })

        _settingsViewModel.stateLoading.observe(viewLifecycleOwner, {
            showloading(it)
        })

        _settingsViewModel.stateDeletePassword.observe(viewLifecycleOwner, {
            if (it) {
                deleteHandler()
            }
        })
    }

    private fun confirmListener() {
        val emailView = _view.findViewById<TextInputEditText>(R.id.tietEmailBottomSheet)
        val passwordView = _view.findViewById<TextInputEditText>(R.id.tietPasswordBottomSheet)
        val confirmBtnView = _view.findViewById<MaterialButton>(R.id.mbConfirmBottomSheet)
        confirmBtnView.setOnClickListener {
            hideKeyboard(_view)
            val emailText = emailView.text.toString()
            val passwordText = passwordView.text.toString()
            if (AuthUtil.validateEmailPassword(emailText, passwordText)) {
                _settingsViewModel.getUserCredential(
                    _view,
                    emailText,
                    passwordText
                )
            }
        }
    }

    private fun deleteHandler() {
        snackBarMessage(getString(R.string.conta_excluida))
        AuthUtil.clearUserInfo(requireActivity())
        Handler(Looper.getMainLooper()).postDelayed({
            val navController = findNavController()
            navController.navigate(R.id.action_bottomsheetPasswordFragment_to_loginFragment)
        }, 1000)
    }

    private fun snackBarMessage(message: String) {
        Snackbar.make(_view, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showloading(status: Boolean) {
        val progressBarView = _view.findViewById<ProgressBar>(R.id.pbProgressBarBottomSheet)
        when {
            status -> {
                progressBarView.visibility = View.VISIBLE
            }
            else -> {
                progressBarView.visibility = View.GONE
            }
        }
    }
}