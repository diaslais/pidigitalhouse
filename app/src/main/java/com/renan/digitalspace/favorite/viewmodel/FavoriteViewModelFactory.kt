package com.renan.digitalspace.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renan.digitalspace.favorite.repository.FavoriteRepository

class FavoriteViewModelFactory(
    private val repository: FavoriteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteViewModel(repository) as T
    }
}