package com.nasinha.digitalspace.profile.viewmodel

import android.app.Application
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.AuthUtil.saveUserName
import com.nasinha.digitalspace.utils.Constants
import com.nasinha.digitalspace.utils.ProfileUtils

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    var stateUserEmail: MutableLiveData<Boolean> = MutableLiveData()
    var stateUserName: MutableLiveData<Boolean> = MutableLiveData()
    var stateUserImage: MutableLiveData<String> = MutableLiveData()
    var stateUserPassword: MutableLiveData<Boolean> = MutableLiveData()
    var stateLoading: MutableLiveData<Boolean> = MutableLiveData()
    var stateDelete = MutableLiveData<Boolean>()
    var stateDeletePassword = MutableLiveData<Boolean>()
    var error: MutableLiveData<String> = MutableLiveData()

    fun updateUserName(
        view: View,
        userName: String,
    ) {
        stateLoading.value = true

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {

            user.updateProfile(userProfileChangeRequest {
                displayName = userName
            }).addOnCompleteListener { task ->

                stateLoading.value = false
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
    }

    fun updateUserEmail(
        view: View,
        newEmail: String,
        oldEmail: String,
        password: String
    ) {
        val user = FirebaseAuth.getInstance().currentUser
        stateLoading.value = true

        if (user != null) {
            val credential = EmailAuthProvider.getCredential(oldEmail, password)

            user.reauthenticate(credential).addOnCompleteListener { task ->
                when {
                    task.isSuccessful -> {
                        user.updateEmail(newEmail).addOnCompleteListener { task ->
                            when {
                                task.isSuccessful -> {
                                    emailVerification(view, user)
                                }
                                else -> {
                                    errorMessage(view.context.getString(R.string.falha_email))
                                    stateLoading.value = false
                                    stateUserEmail.value = false
                                }
                            }
                        }
                    }
                    else -> {
                        errorMessage(view.context.getString(R.string.falha_email))
                        stateLoading.value = false
                        stateUserEmail.value = false
                    }
                }
            }
        }
    }

    private fun emailVerification(view: View, user: FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener { taskVerification ->
            stateLoading.value = false
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

    fun updateUserPhoto(view: View, imageUri: Uri?) {
        var imageUrl: String = ""
        val user = FirebaseAuth.getInstance().currentUser
        stateLoading.value = true

        if (imageUri != null && user != null) {
            val storageReference = FirebaseStorage.getInstance()
                .reference
                .child(
                    "USER_IMAGE_" +
                            System.currentTimeMillis() +
                            "." +
                            ProfileUtils.getFileExtension(view, imageUri)
                )

            storageReference.putFile(imageUri)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->

                        user.updateProfile(userProfileChangeRequest {
                            photoUri = uri
                        }).addOnCompleteListener { task ->
                            stateLoading.value = false

                            when {
                                task.isSuccessful -> {
                                    imageUrl = uri.toString()
                                    stateUserImage.value = imageUrl
                                }
                                else -> {
                                    errorMessage(view.context.getString(R.string.erro_image_perfil))
                                }
                            }
                        }
                    }
                }.addOnFailureListener {
                    stateLoading.value = false
                    errorMessage(view.context.getString(R.string.erro_obter_imagem))
                }
        } else {
            stateLoading.value = false
        }
    }

    fun updateUserPassword(
        view: View,
        userEmail: String,
        oldUserPassword: String,
        newUserPassword: String
    ) {
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            val credential = EmailAuthProvider.getCredential(userEmail, oldUserPassword)
            stateLoading.value = true
            user.reauthenticate(credential).addOnCompleteListener { task ->
                when {
                    task.isSuccessful -> {
                        user.updatePassword(newUserPassword)
                            .addOnCompleteListener { newPasswordTask ->
                                stateLoading.value = false
                                when {
                                    newPasswordTask.isSuccessful -> {
                                        stateUserPassword.value = true
                                    }
                                    else -> {
                                        errorMessage(view.context.getString(R.string.erro_alterar_senha))
                                    }
                                }
                            }
                    }
                    else -> {
                        stateLoading.value = false
                        errorMessage(view.context.getString(R.string.erro_autenticacao))
                    }
                }
            }
        }
    }

    fun getUserCredential(view: View, userEmail: String?, userPassword: String?) {
        val user = FirebaseAuth.getInstance().currentUser
        stateLoading.value = true
        if (user != null) {
            when (AuthUtil.getUserProvider(view.context)) {
                Constants.PASSWORD -> {
                    Log.d(TAG, "email user")
                    if (AuthUtil.validateEmailPassword(userEmail, userPassword)) {
                        val credential =
                            EmailAuthProvider.getCredential(userEmail!!, userPassword!!)
                        reauthenticateUser(user, credential)
                    } else {
                        stateLoading.value = false
                        Log.d(TAG, "email user fail auth")
                        errorMessage("Preencha os campos corretamente")
                    }
                }
                Constants.GOOGLECOM -> {
                    Log.d(TAG, "google user")
                    val acc = GoogleSignIn.getLastSignedInAccount(view.context)
                    val credential = GoogleAuthProvider.getCredential(acc?.idToken, null)
                    reauthenticateUser(user, credential)
                }
                Constants.FACEBOOKCOM -> {
                    Log.d(TAG, "facebook user")
                    val acc = AccessToken.getCurrentAccessToken().token.toString()
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
                    Log.d(TAG, "reauthenticated user")
                    deleteUser(user)
                }
                else -> {
                    stateLoading.value = false
                    Log.d(TAG, "reauthenticate fail")
                    errorMessage("Não foi possível autenticar o usuário")
                }
            }
        }
    }

    fun deleteUser(user: FirebaseUser) {
        user.delete().addOnCompleteListener { task ->
            stateLoading.value = false
            when {
                task.isSuccessful -> {
                    Log.d(TAG, "user deleted")
                    when {
                        (AuthUtil.getUserProvider(getApplication()) == Constants.PASSWORD) -> {
                            stateDeletePassword.value = true
                        }
                        else -> {
                            stateDelete.value = true
                        }
                    }
                }
                else -> {
                    Log.d(TAG, "fail to delete user")
                    errorMessage("Não foi possível excluir a conta")
                    stateDelete.value = false
                }
            }
        }
    }

    private fun errorMessage(s: String) {
        error.value = s
    }

    companion object {
        const val TAG = "settingsDelete"
    }
}
