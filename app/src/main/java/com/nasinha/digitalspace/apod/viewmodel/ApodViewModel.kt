package com.nasinha.digitalspace.apod.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.nasinha.digitalspace.apod.repository.ApodRepository
import kotlinx.coroutines.Dispatchers
import java.io.IOException

class ApodViewModel(
    private val repository: ApodRepository
) : ViewModel() {

    fun getDataApod() =
        liveData(Dispatchers.IO) {

            try {
                val explanation = repository.getData()

                emit(explanation)

            }catch(e: Exception) {
                emit(e.message)
            }


        }

    class ApodViewModelFactory(
        private val repository: ApodRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ApodViewModel(repository) as T
        }

    }

}