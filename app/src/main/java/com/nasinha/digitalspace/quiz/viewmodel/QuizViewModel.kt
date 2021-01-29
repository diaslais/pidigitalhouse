package com.nasinha.digitalspace.quiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.nasinha.digitalspace.quiz.entity.Score
import com.nasinha.digitalspace.quiz.repository.QuizRepository
import kotlinx.coroutines.Dispatchers

class QuizViewModel (private val repository: QuizRepository): ViewModel() {

    //quiz questions
    val questionsList = repository.readQuestionsData().asLiveData()

    fun readQuestionsData() = liveData(Dispatchers.IO) {
        val result = repository.readQuestionsData()
        emit(result)
    }

    //quiz score
    fun addScore(score: Score) = liveData(Dispatchers.IO) {
            repository.addScore(score)
            emit(true)
    }

    fun deleteScore(score: Score) = liveData(Dispatchers.IO) {
        repository.deleteScore(score)
        emit(true)
    }

    fun readScoreData(userId: String) = liveData(Dispatchers.IO) {
        val result = repository.readScoreData(userId)
        emit(result)
    }


    class QuizViewModelFactory(private val repository: QuizRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return QuizViewModel(repository) as T
        }
    }
}

