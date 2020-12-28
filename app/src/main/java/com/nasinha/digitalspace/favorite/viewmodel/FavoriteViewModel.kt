package com.nasinha.digitalspace.favorite.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nasinha.digitalspace.authentication.AppUtil
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity
import com.nasinha.digitalspace.favorite.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers

class FavoriteViewModel(
    private val repository: FavoriteRepository
) : ViewModel() {

    fun addFavorite(favorite: FavoriteEntity) = liveData(Dispatchers.IO) {
        repository.addFavorite(favorite)
        emit(favorite)
    }

    fun getAllFavorite(userId: String) = liveData(Dispatchers.IO) {
        emit(repository.getAll(userId).filter { it.active })
    }

    fun deleteOne(favorite: FavoriteEntity) = liveData(Dispatchers.IO) {
        repository.deleteOne(favorite)
        emit(true)
    }

    fun checkFavorite(activity: Activity, image: String) = liveData(Dispatchers.IO) {
        val check = repository.checkFavorite(image, AppUtil.getUserId(activity.application)!!)

        if (check > 0) {
            emit(true)
        } else {
            emit(false)
        }
    }

    fun deleteFavoriteItem(image: String, userId: String) = liveData(Dispatchers.IO) {
        repository.deleteFavoriteItem(image, userId)
        emit(true)
    }

    fun deleteAll() = liveData(Dispatchers.IO) {
        repository.deleteAll()
        emit(true)
    }
}