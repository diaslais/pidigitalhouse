package com.nasinha.digitalspace.developer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nasinha.digitalspace.developer.repository.DeveloperRepository

class DeveloperViewModelFactory(
    private val repository: DeveloperRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DeveloperViewModel(repository) as T
    }
}