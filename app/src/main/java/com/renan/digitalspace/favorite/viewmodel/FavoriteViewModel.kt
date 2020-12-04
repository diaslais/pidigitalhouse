package com.renan.digitalspace.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.renan.digitalspace.favorite.model.FavoriteModel
import com.renan.digitalspace.favorite.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers

class FavoriteViewModel(
    private val repository: FavoriteRepository
) : ViewModel() {

    private lateinit var selectedFavorite: MutableList<FavoriteModel>

    fun getDataFavorite() = liveData(Dispatchers.IO) {
        repository.getFavorites {
            selectedFavorite = it
        }
        emit(selectedFavorite)
    }
}