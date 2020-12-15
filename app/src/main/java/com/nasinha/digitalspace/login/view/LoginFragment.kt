package com.nasinha.digitalspace.login.view

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.nasinha.digitalspace.R


class LoginFragment : Fragment() {


    private lateinit var _view: View
    private lateinit var callbackManager: CallbackManager
    private val button: Button by lazy { _view.findViewById<Button>(R.id.login_button) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val contextThemeWrapper: Context = ContextThemeWrapper(activity, R.style.AppTheme_Login)

        // clone the inflater using the ContextThemeWrapper
        val localInflater = inflater.cloneInContext(contextThemeWrapper)

        // inflate the layout using the cloned inflater, not default inflater
        return localInflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view
        val btn = _view.findViewById<ImageButton>(R.id.imFacebookLogin)
        checkBoxHandler()

        val argEmail = arguments?.getString("email")

        if (argEmail != null) {
            setEmail(argEmail)
        }

        navigationHandler()

        callbackManager = CallbackManager.Factory.create()
        button.setOnClickListener { loginFacebook() }
        btn.setOnClickListener { button.performClick() }

    }


    private fun checkBoxHandler() {
        val checkBox = _view.findViewById<CheckBox>(R.id.checkBoxLogin)

        val prefs = _view.context.getSharedPreferences(APP_NAME, MODE_PRIVATE)

        val prefsChecked = prefs.getBoolean(SAVED_PREFS, false)

        checkBox.isChecked = prefsChecked

        if (checkBox.isChecked) {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_explorationFragment)
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(SAVED_PREFS, isChecked).apply()
        }
    }


    private fun navigationHandler() {
        val navController = Navigation.findNavController(_view)

        _view.findViewById<MaterialButton>(R.id.mbLoginLogin).setOnClickListener {
            errorHandler()
            navigateLogin(navController)
        }
        navigateSignup(navController, R.id.imEmailLogin)
        navigateSignup(navController, R.id.imGoogleLogin)
    }

    private fun setEmail(emailString: String) {
        val emailText = _view.findViewById<TextInputEditText>(R.id.tietEmailLogin)
        emailText.setText(emailString)
        val passwordText = _view.findViewById<TextInputEditText>(R.id.tietPasswordLogin)
        passwordText.requestFocus()
    }

    private fun errorHandler() {
        val emailText = _view.findViewById<TextInputEditText>(R.id.tietEmailLogin)
        val emailLayout = _view.findViewById<TextInputLayout>(R.id.tilEmailLogin)
        val passwordText = _view.findViewById<TextInputEditText>(R.id.tietPasswordLogin)
        val passwordLayout = _view.findViewById<TextInputLayout>(R.id.tilPasswordLogin)

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

    private fun navigateLogin(navController: NavController) {
        val email = _view.findViewById<TextInputEditText>(R.id.tietEmailLogin)
        val password = _view.findViewById<TextInputEditText>(R.id.tietPasswordLogin)

        if (email.text.toString().isEmpty() || password.text.toString().isEmpty()) {
            hideKeyboard()
            val toast =
                Toast.makeText(context, getString(R.string.toast_campo_vazio), Toast.LENGTH_LONG)
            toast.show()
        } else {
            hideKeyboard()
            navController.navigate(R.id.action_loginFragment_to_explorationFragment)
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            _view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(_view.windowToken, 0)
    }

    private fun navigateSignup(navController: NavController, button: Int) {
        _view.findViewById<ImageButton>(button).setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }

    companion object {
        const val APP_NAME = "DigitalSpace"
        const val SAVED_PREFS = "SAVED_PREFS"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
    private fun loginFacebook() {
        val instanceFirebase = LoginManager.getInstance()

        instanceFirebase.logInWithReadPermissions(this, listOf("email", "public_profile"))
        instanceFirebase.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

            override fun onSuccess(loginResult: LoginResult) {
                val credential: AuthCredential = FacebookAuthProvider.getCredential(loginResult.accessToken.token)
                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { irParaHome(loginResult.accessToken.userId) }
            }

            override fun onCancel() {
                Toast.makeText(_view.context, "Cancelado!", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: FacebookException) {
                Toast.makeText(_view.context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun irParaHome(uiid: String) {
        val navController = Navigation.findNavController(_view)
        AppUtil.salvarIdUsuario(_view.context, uiid)
        navController.navigate(R.id.action_loginFragment_to_explorationFragment)

    }

}