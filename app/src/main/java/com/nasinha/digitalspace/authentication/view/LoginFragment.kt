package com.nasinha.digitalspace.authentication.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.authentication.AppUtil
import com.nasinha.digitalspace.authentication.viewmodel.AuthenticatorViewModel
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {


    private lateinit var _view: View
    private lateinit var callbackManager: CallbackManager
    private val signupEmailBtn: Button by lazy { _view.findViewById(R.id.imEmailButton) }
    private val authenticatorViewModel: AuthenticatorViewModel by lazy {
        ViewModelProvider(this).get(AuthenticatorViewModel::class.java)
    }


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
        val facebookLoginBtn = _view.findViewById<ImageButton>(R.id.imFacebookLogin)


        navigationHandler()
        checkUserId()

        callbackManager = CallbackManager.Factory.create()
        signupEmailBtn.setOnClickListener {
            hideKeyboard()
            loginFacebook()
        }
        facebookLoginBtn.setOnClickListener {
            hideKeyboard()
            signupEmailBtn.performClick()
        }

    }


    private fun navigationHandler() {
        val navController = Navigation.findNavController(_view)

        _view.findViewById<MaterialButton>(R.id.mbLoginLogin).setOnClickListener {
            hideKeyboard()
            navigateLogin(navController)
        }
        navigateSignup(navController, R.id.imEmailLogin)
        navigateSignup(navController, R.id.imGoogleLogin)
    }

    private fun checkUserId() {
/*        if (authenticatorViewModel.getCurrentUser() != null) {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_explorationFragment)
        }*/
        if (AppUtil.getUserId(requireActivity().application) != "") {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_explorationFragment)
        }
    }


    private fun navigateLogin(navController: NavController) {
        val email = _view.findViewById<TextInputEditText>(R.id.tietEmailLogin).text.toString()
        val password = _view.findViewById<TextInputEditText>(R.id.tietPasswordLogin).text.toString()

        when {
            AppUtil.validateEmailPassword(email, password) -> {
                authenticatorViewModel.loginEmailPassword(email, password)
            }
            else -> {
                Snackbar.make(mbLoginLogin, "vish", Snackbar.LENGTH_LONG).show()
            }
        }
        initViewModel()

    }

    private fun initViewModel() {
        authenticatorViewModel.stateLogin.observe(viewLifecycleOwner, { state ->
            state?.let {
                navigateToHome(it)
            }
        })
        authenticatorViewModel.error.observe(viewLifecycleOwner, { loading ->
            loading?.let {
                messageError(it)
            }
        })
    }

    private fun navigateToHome(status: Boolean) {
        val navController = Navigation.findNavController(_view)
        when {
            status -> {
                navController.navigate(R.id.action_loginFragment_to_explorationFragment)
            }
        }
    }

    private fun messageError(it: String) {
        val btnLogin = _view.findViewById<MaterialButton>(R.id.mbLoginLogin)
        Snackbar.make(btnLogin, it, Snackbar.LENGTH_LONG).show()

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun loginFacebook() {
        val instanceFirebase = LoginManager.getInstance()

        instanceFirebase.logInWithReadPermissions(this, listOf("email", "public_profile"))
        instanceFirebase.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

            override fun onSuccess(loginResult: LoginResult) {
                val credential: AuthCredential =
                    FacebookAuthProvider.getCredential(loginResult.accessToken.token)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener { irParaHome(loginResult.accessToken.userId) }
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
        AppUtil.saveUserId(_view.context, uiid)
        navController.navigate(R.id.action_loginFragment_to_explorationFragment)
    }

    companion object {
        const val APP_NAME = "DigitalSpace"
        const val SAVED_PREFS = "SAVED_PREFS"
    }
}