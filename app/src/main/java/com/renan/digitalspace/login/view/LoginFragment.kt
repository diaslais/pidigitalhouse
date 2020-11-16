package com.renan.digitalspace.login.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import com.renan.digitalspace.R
import kotlinx.android.synthetic.main.fragment_login.*


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
            navController.navigate(R.id.fatoAstronomicoFragment2)
        }
        navigateSignup(view, navController, R.id.imEmailLogin)
        navigateSignup(view, navController, R.id.imFacebookLogin)
        navigateSignup(view, navController, R.id.imGoogleLogin)
    }

    private fun navigateSignup(view: View, navController: NavController, button: Int) {
        view.findViewById<ImageButton>(button).setOnClickListener {
            navController.navigate(R.id.signupFragment)
        }
    }
}