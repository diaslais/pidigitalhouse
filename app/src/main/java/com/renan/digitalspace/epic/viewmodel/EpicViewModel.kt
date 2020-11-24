package com.renan.digitalspace.epic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.renan.digitalspace.epic.repository.EpicRepository
import kotlinx.coroutines.Dispatchers

class EpicViewModel(
    private val repository: EpicRepository
) : ViewModel() {

    fun getImageDay() = liveData(Dispatchers.IO) {
        val image = repository.getImageDay()
        emit(image)
    }

    fun getlastDay() = liveData(Dispatchers.IO) {
        val listDates = repository.getLastDay()
        emit(listDates)
    }

    class EpicViewModelFactory(
        private val repository: EpicRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return EpicViewModel(repository) as T
        }

    }

}