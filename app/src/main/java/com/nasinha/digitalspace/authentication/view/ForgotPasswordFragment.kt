package com.nasinha.digitalspace.authentication.view

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nasinha.digitalspace.R


class ForgotPasswordFragment : Fragment() {
    private lateinit var _view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view
        confirmButton()
        closeBtn()

    }

    private fun closeBtn() {
        val btnClose = _view.findViewById<ImageButton>(R.id.ibCloseDialogNameForgotPassword)
        btnClose.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun confirmButton() {
        val editTextEmail = _view.findViewById<EditText>(R.id.tietForgotPassword)
        val tilTextEmail = _view.findViewById<TextInputLayout>(R.id.tilForgotPassword)
        val confirmButton =
            _view.findViewById<MaterialButton>(R.id.confirmButtonForgotPassword)
        var auth = Firebase.auth

        confirmButton.setOnClickListener {

            if (validateEmailFormat(editTextEmail.text.toString())) {

                auth.sendPasswordResetEmail(editTextEmail.text.toString())
                activity?.onBackPressed()
                Toast.makeText(_view.context, "Email enviado", Toast.LENGTH_SHORT).show()

            } else {

                tilTextEmail.error = getString(R.string.invalid_format)

            }

        }
    }

    private fun validateEmailFormat(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}