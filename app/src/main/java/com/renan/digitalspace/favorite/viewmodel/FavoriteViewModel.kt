package com.renan.digitalspace.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.renan.digitalspace.favorite.entity.FavoriteEntity
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
//        emit(repository.getAll().filter { it.active })
        emit(repository.getAll().filter { it.active })
    }

    fun updateOneFavorite(favorite: FavoriteEntity) = liveData(Dispatchers.IO) {
        val deactivatedFavorite = FavoriteEntity(
            favorite.id,
            favorite.image,
            favorite.title,
            favorite.date,
            false
        )
        repository.updateOne(deactivatedFavorite)
        emit(true)
    }

    fun updateActiveAll(active: Boolean, activeStatus: Boolean) = liveData(Dispatchers.IO) {
        emit(repository.updateActiveAll(active, activeStatus))
    }

    fun deleteOne(favorite: FavoriteEntity) = liveData(Dispatchers.IO) {
        repository.deleteOne(favorite)
        emit(true)
    }

    fun deleteAll() = liveData(Dispatchers.IO) {
        repository.deleteAll()
        emit(true)
    }
}