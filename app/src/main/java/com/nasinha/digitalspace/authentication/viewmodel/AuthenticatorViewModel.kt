package com.nasinha.digitalspace.authentication.viewmodel

import android.app.Activity
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.facebook.login.LoginManager
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.authentication.AppUtil

class AuthenticatorViewModel(application: Application) : AndroidViewModel(application) {

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()
    var stateRegister: MutableLiveData<Boolean> = MutableLiveData()
    var stateLogin: MutableLiveData<Boolean> = MutableLiveData()

    fun registerUser(activity: Activity, name: String, email: String, password: String) {
        loading.value = true

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                loading.value = false

                val profileUpdateName = userProfileChangeRequest {
                    displayName = name
                }

                when {
                    task.isSuccessful -> {
                        val user = FirebaseAuth.getInstance().currentUser

                        user!!.updateProfile(profileUpdateName).addOnCompleteListener {
                            user.sendEmailVerification().addOnCompleteListener {
                                stateRegister.value = true
                            }
                        }
                    }
                    else -> {
                        errorMessage(activity.getString(R.string.autenticacao_falhou))
                        stateRegister.value = false
                        loading.value = false
                    }
                }

            }
    }


    fun loginEmailPassword(activity: Activity, email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult?> ->
                when {
                    task.isSuccessful -> {
                        if (FirebaseAuth.getInstance().currentUser!!.isEmailVerified) {
                            val currentUser = FirebaseAuth.getInstance().currentUser

                            AppUtil.saveUserId(
                                getApplication(),
                                currentUser?.uid
                            )

                            AppUtil.saveUserName(
                                getApplication(),
                                currentUser?.displayName
                            )

                            AppUtil.saveUserEmail(
                                getApplication(),
                                currentUser?.email
                            )
                            stateLogin.value = true
                        } else {
                            errorMessage(activity.getString(R.string.verificar_email))
                            stateLogin.value = false
                            loading.value = false
                        }
                    }
                    else -> {
                        errorMessage(activity.getString(R.string.autenticacao_falhou))
                        stateLogin.value = false
                        loading.value = false
                    }
                }
            }
    }

    fun signOutUser(activity: Activity) {
        AppUtil.clearUserInfo(activity)
        FirebaseAuth.getInstance().signOut()
        LoginManager.getInstance().logOut()
    }

    private fun errorMessage(s: String) {
        error.value = s
    }


}