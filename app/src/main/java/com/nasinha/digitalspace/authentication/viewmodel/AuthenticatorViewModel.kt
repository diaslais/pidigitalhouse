package com.nasinha.digitalspace.authentication.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.facebook.login.LoginManager
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.nasinha.digitalspace.authentication.AppUtil

class AuthenticatorViewModel(application: Application) : AndroidViewModel(application) {

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()
    var stateRegister: MutableLiveData<Boolean> = MutableLiveData()
    var stateLogin: MutableLiveData<Boolean> = MutableLiveData()
    var stateUid: String? = null

    fun registerUser(email: String, password: String) {
        loading.value = true

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                loading.value = false
                when {
                    task.isSuccessful -> {
                        AppUtil.saveUserId(
                            getApplication(),
                            FirebaseAuth.getInstance().currentUser?.uid
                        )
                        stateRegister.value = true
                    }
                    else -> {
                        errorMessage("Autenticação falhou!")
                        stateRegister.value = false
                        loading.value = false
                    }
                }

            }
    }


    fun loginEmailPassword(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult?> ->
                when {
                    task.isSuccessful -> {
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
                    }
                    else -> {
                        errorMessage("Autenticação falhou!")
                        stateLogin.value = false
                        loading.value = false
                    }
                }


            }
    }

/*    fun getCurrentUser(): String? {
        if (FirebaseAuth.getInstance().currentUser != null) {
            stateUid = FirebaseAuth.getInstance().currentUser!!.uid
            return stateUid
        }
        return null
    }*/

    fun signOutUser(activity: Activity) {
        AppUtil.clearUserInfo(activity)
        FirebaseAuth.getInstance().signOut()
        LoginManager.getInstance().logOut()
    }

    private fun errorMessage(s: String) {
        error.value = s
    }


}