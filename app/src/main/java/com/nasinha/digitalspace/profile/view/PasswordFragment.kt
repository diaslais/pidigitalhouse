package com.nasinha.digitalspace.profile.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.profile.viewmodel.ProfileViewModel
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.AuthUtil.getUserEmail
import com.nasinha.digitalspace.utils.AuthUtil.getUserProvider
import com.nasinha.digitalspace.utils.AuthUtil.hideKeyboard
import com.nasinha.digitalspace.utils.AuthUtil.validatePassword
import com.nasinha.digitalspace.utils.Constants.PASSWORD

class PasswordFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        profileViewModel()
        initViewModel()
        activateInputsHandler()
        backBtnHandler()
    }

    private fun profileViewModel() {
        _profileViewModel = ViewModelProvider(this).get(
            ProfileViewModel::class.java
        )
    }

    private fun initViewModel() {
        _profileViewModel.stateLoading.observe(viewLifecycleOwner, {
            showLoading(it)
        })

        _profileViewModel.stateUserPassword.observe(viewLifecycleOwner, {
            if (it) {
                snackBarMessage("Senha alterada com sucesso")
                Handler(Looper.getMainLooper()).postDelayed({
                    requireActivity().onBackPressed()
                }, 1000)
            }
        })
    }

    private fun activateInputsHandler() {
        val oldPass = _view.findViewById<TextInputEditText>(R.id.tietOldPasswordPassword)
        val newPass = _view.findViewById<TextInputEditText>(R.id.tietNewPasswordPassword)
        val confirmNewPass =
            _view.findViewById<TextInputEditText>(R.id.tietNewPasswordConfirmationPassword)
        val confirmPassBtn = _view.findViewById<MaterialButton>(R.id.mbConfirmPasswordPassword)

        if (getUserProvider(requireActivity()) == PASSWORD) {
            oldPass.isEnabled = true
            newPass.isEnabled = true
            confirmNewPass.isEnabled = true
            confirmPassBtn.isEnabled = true
            confirmButtonHandler()
        }
    }

    private fun confirmButtonHandler() {
        val oldPass = _view.findViewById<TextInputEditText>(R.id.tietOldPasswordPassword)
        val newPass = _view.findViewById<TextInputEditText>(R.id.tietNewPasswordPassword)
        val confirmNewPass =
            _view.findViewById<TextInputEditText>(R.id.tietNewPasswordConfirmationPassword)
        val confirmPassBtn = _view.findViewById<MaterialButton>(R.id.mbConfirmPasswordPassword)

        confirmPassBtn.setOnClickListener {
            hideKeyboard(_view)
            when {
                oldPass.text.toString().isEmpty() || newPass.text.toString()
                    .isEmpty() || newPass.text.toString() != confirmNewPass.text.toString() -> {
                    snackBarMessage(getString(R.string.erro_validacao_campo))
                }
                !validatePassword(newPass.text.toString()) -> {
                    snackBarMessage(getString(R.string.erro_senha_6_algarismos))
                }
                else -> {
                    _profileViewModel.updateUserPassword(
                        _view,
                        getUserEmail(requireActivity())!!,
                        oldPass.text.toString(),
                        newPass.text.toString()
                    )
                }
            }
        }

    }


    private fun backBtnHandler() {
        val backBtn = _view.findViewById<ImageButton>(R.id.ibBackPassword)
        backBtn.setOnClickListener {
            hideKeyboard(_view)
            requireActivity().onBackPressed()
        }
    }

    private fun snackBarMessage(message: String) {
        Snackbar.make(
            _view,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun showLoading(status: Boolean) {
        val loadingBtn = _view.findViewById<ProgressBar>(R.id.pbLoadPassword)
        when {
            status -> {
                loadingBtn.visibility = View.VISIBLE
            }
            else -> {
                loadingBtn.visibility = View.GONE
            }
        }
    }
}