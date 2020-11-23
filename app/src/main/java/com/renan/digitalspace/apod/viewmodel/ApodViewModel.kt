package com.renan.digitalspace.apod.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.renan.digitalspace.apod.model.ApodResponseModel
import com.renan.digitalspace.apod.repository.ApodRepository
import kotlinx.coroutines.Dispatchers

class ApodViewModel(
    private val repository: ApodRepository
) : ViewModel() {

    fun getDataApod() =
        liveData(Dispatchers.IO) {
            val explanation = repository.getData()
//            val url = repository.getData().url
//            val date = repository.getData().date
//            val title = repository.getData().title

            emit(explanation)
//            emit(title)
//            emit(url)
//            emit(date)
        }

    class ApodViewModelFactory(
        private val repository: ApodRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ApodViewModel(repository) as T
        }

    }

}