package com.nasinha.digitalspace.authentication.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.utils.AuthUtil.hideKeyboard
import com.nasinha.digitalspace.utils.AuthUtil.validateNameEmailPassword
import com.nasinha.digitalspace.authentication.viewmodel.AuthenticatorViewModel
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {


    private lateinit var _view: View

    private val viewModel: AuthenticatorViewModel by lazy {
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

        // Inflate the layout for this fragment
        return localInflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view

        registerListener()
        loginListener()
    }

    private fun registerListener() {
        val btnRegister = _view.findViewById<MaterialButton>(R.id.btnSignup)

        btnRegister.setOnClickListener {
            hideKeyboard(_view)
            validateRegister()
        }
        initViewModel()
    }

    private fun loginListener() {
        val btnLogin = _view.findViewById<MaterialButton>(R.id.mbLoginSignup)

        btnLogin.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun validateRegister() {
        val name = _view.findViewById<TextInputEditText>(R.id.tietNameSignup).text.toString()
        val email = _view.findViewById<TextInputEditText>(R.id.tietEmailSignup).text.toString()
        val password =
            _view.findViewById<TextInputEditText>(R.id.tietPasswordSignup).text.toString()

        when {
            validateNameEmailPassword(name, email, password) -> {
                viewModel.registerUser(requireActivity(), name, email, password)
            }
        }

    }

    private fun initViewModel() {
        viewModel.stateRegister.observe(viewLifecycleOwner, { state ->
            state?.let {
                navigateToLogin(it)
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, { state ->
            state?.let {
                showloading(it)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, { state ->
            state?.let {
                messageError(it)
            }
        })
    }

    private fun messageError(it: String) {
        val btnRegister = _view.findViewById<MaterialButton>(R.id.btnSignup)
        Snackbar.make(btnRegister, it, Snackbar.LENGTH_LONG).show()

    }

    private fun showloading(status: Boolean) {
        when {
            status -> {
                pb_register.visibility = View.VISIBLE
            }
            else -> {
                pb_register.visibility = View.GONE
            }
        }
    }


    private fun navigateToLogin(isRegistered: Boolean) {
        if (isRegistered) {
            Snackbar.make(_view, getString(R.string.email_verificacao), Snackbar.LENGTH_LONG).show()
            Handler(Looper.getMainLooper()).postDelayed({
                requireActivity().onBackPressed()
            }, 500)
        }
    }
}