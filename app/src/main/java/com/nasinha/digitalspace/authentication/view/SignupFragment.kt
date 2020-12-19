package com.nasinha.digitalspace.authentication.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.authentication.viewmodel.AuthenticatorViewModel
import com.nasinha.digitalspace.authentication.AppUtil
import com.nasinha.digitalspace.authentication.AppUtil.validateNameEmailPassword

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

        val btnRegister = _view.findViewById<MaterialButton>(R.id.btnSignup)

        btnRegister.setOnClickListener {
            validateRegister()
        }


    }

    private fun validateRegister() {
        val name = _view.findViewById<TextInputEditText>(R.id.tietNameSignup).text.toString()
        val email = _view.findViewById<TextInputEditText>(R.id.tietEmailSignup).text.toString()
        val password = _view.findViewById<TextInputEditText>(R.id.tietPasswordSignup).text.toString()

        when {
            validateNameEmailPassword(name, email, password) -> {
                viewModel.registerUser(email, password)
            }
        }
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.stateRegister.observe(viewLifecycleOwner, { state ->
            state?.let {
                navigateToHome(it)
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
        Snackbar.make(btnRegister,it,Snackbar.LENGTH_LONG).show()

    }

    private fun showloading(status: Boolean) {
        TODO("Not yet implemented")
    }


    private fun navigateToHome(status: Boolean) {
        val navController = Navigation.findNavController(_view)
        when {
            status -> {
                navController.navigate(R.id.action_signupFragment_to_explorationFragment)
            }
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            _view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(_view.windowToken, 0)
    }
}