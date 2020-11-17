package com.renan.digitalspace.signup.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.renan.digitalspace.R
import org.w3c.dom.Text

class SignupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val contextThemeWrapper: Context = ContextThemeWrapper(activity, R.style.AppTheme_Login)

        // clone the inflater using the ContextThemeWrapper
        val localInflater = inflater.cloneInContext(contextThemeWrapper)

        // inflate the layout using the cloned inflater, not default inflater
        var view = localInflater.inflate(R.layout.fragment_signup, container, false)

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        view.findViewById<MaterialButton>(R.id.mbSignupSignup).setOnClickListener {
            errorHandler(view)
            navigateSignup(view, navController)
        }
        view.findViewById<MaterialButton>(R.id.mbLoginSignup).setOnClickListener {
            navController.navigate(R.id.loginFragment)
        }
    }


    private fun navigateSignup(view: View, navController: NavController) {
        val name =
            view.findViewById<TextInputEditText>(R.id.tietNameSignup)
        val email =
            view.findViewById<TextInputEditText>(R.id.tietEmailSignup)
        val password =
            view.findViewById<TextInputEditText>(R.id.tietPasswordSignup)
        val passwordConfirmation =
            view.findViewById<TextInputEditText>(R.id.tietPasswordConfirmationSignup)

        if (name.text.toString().isEmpty() ||
            email.text.toString().isEmpty() ||
            password.text.toString().isEmpty()
        ) {
            val toast =
                Toast.makeText(context, getString(R.string.error_confirmacao), Toast.LENGTH_LONG)
            toast.show()
        } else if (password.text.toString() != passwordConfirmation.text.toString()) {
            val toast =
                Toast.makeText(context, getString(R.string.error_confirmacao), Toast.LENGTH_LONG)
            toast.show()
        } else {
            navController.navigate(R.id.loginFragment)
            val toast =
                Toast.makeText(context, getString(R.string.confirmacao), Toast.LENGTH_LONG)
            toast.show()
        }
    }

    private fun errorHandler(view: View) {
        val nameText = view.findViewById<TextInputEditText>(R.id.tietNameSignup)
        val nameLayout = view.findViewById<TextInputLayout>(R.id.tilNameSignup)
        val emailText = view.findViewById<TextInputEditText>(R.id.tietEmailSignup)
        val emailLayout = view.findViewById<TextInputLayout>(R.id.tilEmailSignup)
        val passwordText = view.findViewById<TextInputEditText>(R.id.tietPasswordSignup)
        val passwordLayout = view.findViewById<TextInputLayout>(R.id.tilPasswordSignup)
        val passwordConfirmationText =
            view.findViewById<TextInputEditText>(R.id.tietPasswordConfirmationSignup)
        val passwordConfirmationLayout =
            view.findViewById<TextInputLayout>(R.id.tilPasswordConfirmationSignup)

        putError(nameText, nameLayout)
        clearError(nameText, nameLayout)
        putError(emailText, emailLayout)
        clearError(emailText, emailLayout)
        putError(passwordText, passwordLayout)
        clearError(passwordText, passwordLayout)

        // checa se a confirmação de password é igual ao password
        passwordConfirmationCheck(
            passwordText,
            passwordConfirmationText,
            passwordConfirmationLayout
        )
    }

    private fun passwordConfirmationCheck(
        passwordText: TextInputEditText,
        passwordConfirmationText: TextInputEditText,
        passwordConfirmationLayout: TextInputLayout
    ) {
        if (passwordConfirmationText.text.toString() != passwordText.text.toString()) {
            passwordConfirmationLayout.isErrorEnabled = true
            passwordConfirmationLayout.error = getString(R.string.error_confirmacao)
        } else {
            passwordConfirmationLayout.isErrorEnabled = false
            passwordConfirmationLayout.error = ""
        }
    }

    private fun putError(text: TextInputEditText?, layout: TextInputLayout?) {
        if (text?.text.toString().isEmpty()) {
            layout?.isErrorEnabled = true
            layout?.error = getString(R.string.error_vazio)
        }
    }

    private fun clearError(text: TextInputEditText?, layout: TextInputLayout?) {
        text?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                layout!!.isErrorEnabled = false
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