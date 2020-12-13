package com.nasinha.digitalspace.developer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nasinha.digitalspace.developer.entity.DeveloperEntity
import com.nasinha.digitalspace.developer.repository.DeveloperRepository
import kotlinx.coroutines.Dispatchers

class DeveloperViewModel(
    private val repository: DeveloperRepository
) : ViewModel() {

    fun addDeveloper(developer: DeveloperEntity) = liveData(Dispatchers.IO) {
        repository.addDeveloper(developer)
        emit(developer)
    }

    fun getAllDeveloper() = liveData(Dispatchers.IO) {
        emit(repository.getAll())
    }

    fun deleteAll() = liveData(Dispatchers.IO) {
        repository.deleteAll()
        emit(true)
    }
}