package com.nasinha.digitalspace.quiz.viewmodel

import androidx.lifecycle.*
import com.nasinha.digitalspace.planetsmenu.model.Planet
import com.nasinha.digitalspace.quiz.entity.Question
import com.nasinha.digitalspace.quiz.repository.QuizRepository
import kotlinx.coroutines.Dispatchers

class QuizViewModel (private val repository: QuizRepository): ViewModel() {

    fun addQuestion(question: Question){
        liveData(Dispatchers.IO) {
            repository.addQuestion(question)
            emit(true)
        }
    }

    fun readQuestionsData() = liveData(Dispatchers.IO) {
        emit(repository.readQuestionsData())
    }

    class QuizViewModelFactory(private val repository: QuizRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return QuizViewModel(repository) as T
        }
    }
}

