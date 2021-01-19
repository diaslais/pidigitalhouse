package com.nasinha.digitalspace.profile.viewmodel

import android.app.Application
import android.net.Uri
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.utils.AuthUtil.getUserEmail
import com.nasinha.digitalspace.utils.AuthUtil.getUserName
import com.nasinha.digitalspace.utils.AuthUtil.getUserProvider
import com.nasinha.digitalspace.utils.AuthUtil.saveUserName
import com.nasinha.digitalspace.utils.Constants
import com.nasinha.digitalspace.utils.Constants.PASSWORD
import com.nasinha.digitalspace.utils.ProfileUtils

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    var stateUserEmail: MutableLiveData<Boolean> = MutableLiveData()
    var stateUserName: MutableLiveData<Boolean> = MutableLiveData()
    var stateUserImage: MutableLiveData<String> = MutableLiveData()
    var stateLoading: MutableLiveData<Boolean> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()

    fun updateUserName(
        view: View,
        userName: String,
    ) {
        stateLoading.value = true

        val user = FirebaseAuth.getInstance().currentUser
        user!!.updateProfile(userProfileChangeRequest {
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

    fun updateUserEmail(
        view: View,
        email: String,
    ) {
        val user = FirebaseAuth.getInstance().currentUser
        stateLoading.value = true

        user!!.updateEmail(email).addOnCompleteListener { task ->
            when {
                task.isSuccessful -> {
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
                else -> {
                    errorMessage(view.context.getString(R.string.falha_email))
                    stateLoading.value = false
                    stateUserEmail.value = false
                }
            }
        }
    }

    fun updateUserPhoto(view: View, imageUri: Uri?) {
        var imageUrl: String = ""
        val user = FirebaseAuth.getInstance().currentUser
        stateLoading.value = true

        if (imageUri != null) {
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

                        user!!.updateProfile(userProfileChangeRequest {
                            photoUri = uri
                        }).addOnCompleteListener { task ->
                            stateLoading.value = false

                            when {
                                task.isSuccessful -> {
                                    imageUrl = uri.toString()
                                    stateUserImage.value = imageUrl
                                }
                                else -> {
                                    errorMessage("Não foi possivel alterar a imagem do perfil")
                                }
                            }
                        }
                    }
                }.addOnFailureListener {
                    stateLoading.value = false
                    errorMessage("Não foi possivel obter imagem")
                }
        } else {
            stateLoading.value = false
        }
    }

    private fun errorMessage(s: String) {
        error.value = s
    }
}