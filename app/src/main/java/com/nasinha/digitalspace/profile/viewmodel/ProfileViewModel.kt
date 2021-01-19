package com.nasinha.digitalspace.profile.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.utils.AuthUtil.getUserEmail
import com.nasinha.digitalspace.utils.AuthUtil.getUserName
import com.nasinha.digitalspace.utils.AuthUtil.getUserProvider
import com.nasinha.digitalspace.utils.AuthUtil.saveUserName
import com.nasinha.digitalspace.utils.Constants.PASSWORD

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    var stateUserEmail: MutableLiveData<Boolean> = MutableLiveData()
    var stateUserName: MutableLiveData<Boolean> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()

    fun updateUserName(
        view: View,
        userName: String,
    ) {
        val user = FirebaseAuth.getInstance().currentUser
        user!!.updateProfile(userProfileChangeRequest {
            displayName = userName
        }).addOnCompleteListener { task ->
            when {
                task.isSuccessful -> {
                    saveUserName(view.context, userName)
                    stateUserName.value = true
                }
                else -> {
                    errorMessage(view.context.getString(R.string.erro_alterar_nome))
                    stateUserName.value = false
                }
            }
        }
    }

    fun updateUserEmail(
        view: View,
        email: String,
    ) {
        val user = FirebaseAuth.getInstance().currentUser

        user!!.updateEmail(email).addOnCompleteListener { task ->
            when {
                task.isSuccessful -> {
                    user.sendEmailVerification().addOnCompleteListener { taskVerification ->
                        when {
                            (taskVerification.isSuccessful) -> {
                                Snackbar.make(
                                    view,
                                    view.context.getString(R.string.email_verificacao),
                                    Snackbar.LENGTH_LONG
                                ).show()
                                stateUserEmail.value = true
                            }
                            else -> {
                                errorMessage(view.context.getString(R.string.problema_email_verificacao))
                            }
                        }
                    }
                }
                else -> {
                    errorMessage(view.context.getString(R.string.falha_email))
                    stateUserEmail.value = false
                }
            }
        }
    }

    private fun errorMessage(s: String) {
        error.value = s
    }
}