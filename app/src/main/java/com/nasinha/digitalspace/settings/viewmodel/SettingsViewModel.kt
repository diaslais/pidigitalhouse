package com.nasinha.digitalspace.settings.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.*
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.Constants.FACEBOOKCOM
import com.nasinha.digitalspace.utils.Constants.GOOGLECOM
import com.nasinha.digitalspace.utils.Constants.PASSWORD

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    var stateLoading = MutableLiveData<Boolean>()
    var stateCredential = MutableLiveData<Boolean>()
    var stateDelete = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun getUserCredential(view: View, userEmail: String?, userPassword: String?) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            when (AuthUtil.getUserProvider(view.context)) {
                PASSWORD -> {
                    Log.d("TAG", "email user")
                    if (AuthUtil.validateEmailPassword(userEmail, userPassword)) {
                        val credential =
                            EmailAuthProvider.getCredential(userEmail!!, userPassword!!)
                        reauthenticateUser(user, credential)
                    } else {
                        Log.d("TAG", "email user fail auth")
                        errorMessage("Preencha os campos corretamente")
                    }
                }
                GOOGLECOM -> {
                    Log.d("TAG", "google user")
                    val acc = GoogleSignIn.getLastSignedInAccount(view.context)
                    val credential = GoogleAuthProvider.getCredential(acc?.idToken, null)
                    reauthenticateUser(user, credential)
                }
                FACEBOOKCOM -> {
                    Log.d("TAG", "facebook user")
                    val acc = FacebookAuthProvider.PROVIDER_ID
                    val credential = FacebookAuthProvider.getCredential(acc)
                    reauthenticateUser(user, credential)
                }
            }

        }
    }

    private fun reauthenticateUser(user: FirebaseUser, credential: AuthCredential) {
        user.reauthenticate(credential).addOnCompleteListener { task ->
            when {
                task.isSuccessful -> {
                    Log.d("TAG", "reauthenticated user")
                    deleteUser(user)
                }
                else -> {
                    Log.d("TAG", "reauthenticate fail")
                    errorMessage("Não foi possível autenticar o usuário")
                }
            }
        }
    }

    fun deleteUser(user: FirebaseUser) {
        user.delete().addOnCompleteListener { task ->
            when {
                task.isSuccessful -> {
                    Log.d("TAG", "user deleted")
                    stateDelete.value = true
                }
                else -> {
                    Log.d("TAG", "fail to delete user")
                    errorMessage("Não foi possível excluir a conta")
                    stateDelete.value = false
                }
            }
        }
    }

    private fun errorMessage(s: String) {
        error.value = s
    }
}