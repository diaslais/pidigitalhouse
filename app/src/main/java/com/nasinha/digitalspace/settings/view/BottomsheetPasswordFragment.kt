package com.nasinha.digitalspace.settings.view

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.authentication.viewmodel.AuthenticatorViewModel
import com.nasinha.digitalspace.profile.viewmodel.ProfileViewModel
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.AuthUtil.hideKeyboard
import com.nasinha.digitalspace.utils.Constants.USER_EMAIL

class BottomsheetPasswordFragment : BottomSheetDialogFragment() {
    private var _newUserEmail: String? = null
    private lateinit var _navController: NavController
    private lateinit var _bottomSheetBiew: View

    private val _profileViewModel: ProfileViewModel by lazy {
        ViewModelProvider(this).get(ProfileViewModel::class.java)
    }
    private val _authenticatorViewModel: AuthenticatorViewModel by lazy {
        ViewModelProvider(this).get(AuthenticatorViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let {
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
        _bottomSheetBiew = view
        _navController = findNavController()

        getArgumentsHandler()
        initViewModel()
        confirmListener()
    }

    private fun getArgumentsHandler() {
        val newEmail = arguments?.getString(USER_EMAIL)
        newEmail?.let {
            _newUserEmail = it
        }
    }

    private fun initViewModel() {
        _profileViewModel.error.observe(viewLifecycleOwner, { s ->
            snackBarMessage(s)
        })

        _profileViewModel.stateLoading.observe(viewLifecycleOwner, {
            showloading(it)
        })

        _profileViewModel.stateUserEmail.observe(viewLifecycleOwner, { state ->
            state?.let {
                if (it) {
                    navigateHome()
                }
            }
        })

        _profileViewModel.stateDeletePassword.observe(viewLifecycleOwner, {
            if (it) {
                navigateHome()
            }
        })
    }


    private fun navigateHome() {
        hideKeyboard(_bottomSheetBiew)
        when (_newUserEmail) {
            null -> {
                snackBarMessage(getString(R.string.conta_excluida))
            }
            else -> {
                snackBarMessage(getString(R.string.email_atualizado_verificar))
            }
        }
        _authenticatorViewModel.signOutUser(requireActivity())

        Handler(Looper.getMainLooper()).postDelayed({
            _navController.navigate(R.id.action_bottomsheetPasswordFragment_to_loginFragment)
        }, 2000)
    }

    private fun confirmListener() {
        val emailView = _bottomSheetBiew.findViewById<TextInputEditText>(R.id.tietEmailBottomSheetPassword)
        val passwordView =
            _bottomSheetBiew.findViewById<TextInputEditText>(R.id.tietPasswordBottomSheetPassword)
        val confirmBtnView = _bottomSheetBiew.findViewById<MaterialButton>(R.id.mbConfirmBottomSheetPassword)
        confirmBtnView.setOnClickListener {
            hideKeyboard(_bottomSheetBiew)
            val emailText = emailView.text.toString()
            val passwordText = passwordView.text.toString()
            when (_newUserEmail) {
                null -> {
                    if (AuthUtil.validateEmailPassword(emailText, passwordText)) {
                        _profileViewModel.getUserCredential(
                            _bottomSheetBiew,
                            emailText,
                            passwordText
                        )
                    }
                }
                else -> {
                    _profileViewModel.updateUserEmail(
                        _bottomSheetBiew,
                        _newUserEmail!!,
                        emailView.text.toString(),
                        passwordView.text.toString()
                    )
                }
            }
        }
    }

    private fun snackBarMessage(message: String) {
        val mb = _bottomSheetBiew.findViewById<MaterialButton>(R.id.mbConfirmBottomSheetPassword)
        Snackbar.make(mb, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showloading(status: Boolean) {
        val progressBarView = _bottomSheetBiew.findViewById<ProgressBar>(R.id.pbProgressBarBottomSheetPassword)
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