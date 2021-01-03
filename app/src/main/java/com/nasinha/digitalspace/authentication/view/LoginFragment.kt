package com.nasinha.digitalspace.authentication.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.Profile
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.authentication.AppUtil
import com.nasinha.digitalspace.authentication.viewmodel.AuthenticatorViewModel
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var callbackManager: CallbackManager
    private val facebookRealBtn: Button by lazy { _view.findViewById(R.id.imFacebookRealLogin) }
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
        lockDrawer(requireActivity())
        _view = view
        val facebookFakeBtn = _view.findViewById<ImageButton>(R.id.imFacebookLogin)

        checkUserId()
//        Email login
        loginHandler()
//        Email signup
        emailSignupHandler()
//        Facebook
        callbackManager = CallbackManager.Factory.create()
        facebookRealBtn.setOnClickListener {
            facebookLoginHandler()
        }
        facebookFakeBtn.setOnClickListener {
            facebookRealBtn.performClick()
        }
//        Google
        googleLoginHandler()
    }

    private fun checkUserId() {
        if (AppUtil.getUserId(requireActivity().application) != "") {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_explorationFragment)
        }
    }

    private fun loginHandler() {
        _view.findViewById<MaterialButton>(R.id.mbLoginLogin).setOnClickListener {
            hideKeyboard()
            navigateLogin()
        }
    }

    private fun emailSignupHandler() {
        val loginBtn = _view.findViewById<ImageButton>(R.id.imEmailLogin)

        loginBtn.setOnClickListener {
            val navController = Navigation.findNavController(_view)
            navController.navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }

    private fun navigateLogin() {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    hideKeyboard()
                    val account = task.getResult(ApiException::class.java)!!
//                    Log.d("GoogleSignIn", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
//                    Log.w("GoogleSignIn", "Google sign in failed", e)
                }
            } else {
                Log.w("GoogleSignIn", exception.toString())
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }


    private fun googleLoginHandler() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        val googleSignInBtn = _view.findViewById<ImageButton>(R.id.imGoogleLogin)

        googleSignInBtn.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val mAuth = FirebaseAuth.getInstance()

        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d("googleSignIn", "signInWithCredential:success")
                    val uiid = mAuth.currentUser?.uid
                    val name = mAuth.currentUser?.displayName
                    AppUtil.saveUserId(requireActivity(), uiid)
                    AppUtil.saveUserName(requireActivity(), name)
                    navigateToHome(!uiid.isNullOrEmpty())
                } else {
                    // If sign in fails, display a message to the user.
//                    Log.w("googleSignIn", "signInWithCredential:failure", task.exception)
                    Snackbar.make(_view, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                }
            }
    }

    private fun facebookLoginHandler() {
        val instanceFirebase = LoginManager.getInstance()

        instanceFirebase.logInWithReadPermissions(this, listOf("email", "public_profile"))
        instanceFirebase.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

            override fun onSuccess(loginResult: LoginResult) {
                val credential: AuthCredential =
                    FacebookAuthProvider.getCredential(loginResult.accessToken.token)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener {
                        val profile = Profile.getCurrentProfile()
                        val name = profile.name
                        irParaHome(loginResult.accessToken.userId, name)
                    }
            }

            override fun onCancel() {
                Toast.makeText(_view.context, "Cancelado!", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: FacebookException) {
                Toast.makeText(_view.context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun irParaHome(uiid: String, name: String?) {
        val navController = Navigation.findNavController(_view)
        AppUtil.saveUserId(requireActivity(), uiid)
        AppUtil.saveUserName(requireActivity(), name)
        navController.navigate(R.id.action_loginFragment_to_explorationFragment)
    }

    companion object {
        private const val RC_SIGN_IN = 100
    }
}