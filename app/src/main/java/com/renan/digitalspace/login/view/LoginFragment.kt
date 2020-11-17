package com.renan.digitalspace.login.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.renan.digitalspace.R


class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val contextThemeWrapper: Context = ContextThemeWrapper(activity, R.style.AppTheme_Login)

        // clone the inflater using the ContextThemeWrapper
        val localInflater = inflater.cloneInContext(contextThemeWrapper)

        // inflate the layout using the cloned inflater, not default inflater
        var view = localInflater.inflate(R.layout.fragment_login, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        view.findViewById<MaterialButton>(R.id.mbLoginLogin).setOnClickListener {
            errorHandler(view)
            navigateLogin(view, navController)
        }
        navigateSignup(view, navController, R.id.imEmailLogin)
        navigateSignup(view, navController, R.id.imFacebookLogin)
        navigateSignup(view, navController, R.id.imGoogleLogin)
    }

    private fun navigateLogin(view: View, navController: NavController) {
        val email = view.findViewById<TextInputEditText>(R.id.tietEmailLogin)
        val password = view.findViewById<TextInputEditText>(R.id.tietPasswordLogin)

        if (email.text.toString().isEmpty() || password.text.toString().isEmpty()) {
            val toast =
                Toast.makeText(context, getString(R.string.preencha_campos), Toast.LENGTH_LONG)
            toast.show()
        } else {
            navController.navigate(R.id.fatoAstronomicoFragment2)
        }
    }

    private fun errorHandler(view: View) {
        val emailText = view.findViewById<TextInputEditText>(R.id.tietEmailLogin)
        val emailLayout = view.findViewById<TextInputLayout>(R.id.tilEmailLogin)
        val passwordText = view.findViewById<TextInputEditText>(R.id.tietPasswordLogin)
        val passwordLayout = view.findViewById<TextInputLayout>(R.id.tilPasswordLogin)

        putError(emailText, emailLayout)
        clearError(emailText, emailLayout)
        putError(passwordText, passwordLayout)
        clearError(passwordText, passwordLayout)
    }

    private fun putError(text: TextInputEditText, layout: TextInputLayout) {
        if (text.text.toString().isEmpty()) {
            layout.isErrorEnabled = true
            layout.error = getString(R.string.error_vazio)
        }
    }

    private fun clearError(text: TextInputEditText, layout: TextInputLayout) {
        text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                layout.isErrorEnabled = false
                layout.error = ""
            }
        })
    }

    private fun navigateSignup(view: View, navController: NavController, button: Int) {
        view.findViewById<ImageButton>(button).setOnClickListener {
            navController.navigate(R.id.signupFragment)
        }
    }
}