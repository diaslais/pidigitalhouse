package com.nasinha.digitalspace.epic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.nasinha.digitalspace.epic.repository.EpicRepository
import kotlinx.coroutines.Dispatchers
import kotlin.Exception

class EpicViewModel(
    private val repository: EpicRepository
) : ViewModel() {

    fun getImageDay() = liveData(Dispatchers.IO) {
        try {
            val image = repository.getImageDay()
            emit(image)
        } catch (e: Exception) {
            emit(e.message)
        }

    }

    fun getlastDay() = liveData(Dispatchers.IO) {
        try {
            val listDates = repository.getLastDay()
            emit(listDates)

        } catch (e: Exception) {
            emit(e.message)
        }

    }

    class EpicViewModelFactory(
        private val repository: EpicRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return EpicViewModel(repository) as T
        }

    }

}