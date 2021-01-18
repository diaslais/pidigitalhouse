package com.nasinha.digitalspace.favorite.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nasinha.digitalspace.utils.AuthUtil
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity
import com.nasinha.digitalspace.favorite.entity.UserEntity
import com.nasinha.digitalspace.favorite.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers

class FavoriteViewModel(
    private val repository: FavoriteRepository
) : ViewModel() {

    fun addFavorite(favorite: FavoriteEntity) = liveData(Dispatchers.IO) {
        repository.addFavorite(favorite)
        emit(favorite)
    }

    fun addUserFavorite(user: UserEntity) = liveData(Dispatchers.IO) {
        repository.addUserFavorite(user)
        emit(user)
    }

    fun getUserWithFavorites(userId: String) = liveData(Dispatchers.IO) {
        val result = repository.getUserWithFavorites(userId)
        emit(result)
    }

    fun updateTitleBr(image: String, title: String) = liveData(Dispatchers.IO) {
        val updateTitle = repository.updateTitleBr(image, title)
        emit(updateTitle)
    }

    fun updateTextBr(image: String, text: String) = liveData(Dispatchers.IO) {
        val updateText = repository.updateTextBr(image, text)
        emit(updateText)
    }

    fun checkFavorite(activity: Activity, image: String) = liveData(Dispatchers.IO) {
        val check = repository.checkFavorite(image, AuthUtil.getUserId(activity.application)!!)

        if (check > 0) {
            emit(true)
        } else {
            emit(false)
        }
    }

    fun getFavorite(image: String) = liveData(Dispatchers.IO) {
        val favorite = repository.getFavorite(image)
        emit(favorite)
    }

    fun deleteFavoriteItem(image: String, userId: String) = liveData(Dispatchers.IO) {
        repository.deleteFavoriteItem(image, userId)
        emit(true)
    }

    fun deleteAllFavorite() = liveData(Dispatchers.IO) {
        repository.deleteAllFavorite()
        emit(true)
    }
}