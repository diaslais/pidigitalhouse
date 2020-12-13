package com.nasinha.digitalspace.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
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

    fun getAllFavorite() = liveData(Dispatchers.IO) {
        emit(repository.getAll().filter { it.active })
    }

    fun deleteOne(favorite: FavoriteEntity) = liveData(Dispatchers.IO) {
        repository.deleteOne(favorite)
        emit(true)
    }

    fun deleteAll() = liveData(Dispatchers.IO) {
        repository.deleteAll()
        emit(true)
    }

    fun setAllFavorites() = liveData(Dispatchers.IO) {
        val favorites = mutableListOf<FavoriteEntity>()
        repository.setFavorites {
            favorites.addAll(it)
        }
        emit(favorites)
    }
}