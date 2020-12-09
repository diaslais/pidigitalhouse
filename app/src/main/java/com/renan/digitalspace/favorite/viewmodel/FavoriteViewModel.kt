package com.renan.digitalspace.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.renan.digitalspace.favorite.entity.FavoriteEntity
import com.renan.digitalspace.favorite.model.FavoriteModel
import com.renan.digitalspace.favorite.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers

class FavoriteViewModel(
    private val repository: FavoriteRepository
) : ViewModel() {

    fun addFavorite(favorite: FavoriteEntity) = liveData(Dispatchers.IO) {
        repository.addFavorite(favorite)
        emit(favorite)
    }

    fun getAllFavorite() = liveData(Dispatchers.IO) {
        emit(repository.getAll())
    }

    fun updateOneFavorite(favorite: FavoriteEntity) = liveData(Dispatchers.IO) {
        repository.updateOne(favorite)
        emit(favorite)
    }

    fun deleteAll() = liveData(Dispatchers.IO) {
        repository.deleteAll()
        emit(true)
    }
}