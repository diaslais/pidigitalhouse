package com.nasinha.digitalspace.quiz.viewmodel

import androidx.lifecycle.*
import com.nasinha.digitalspace.quiz.entity.Question
import com.nasinha.digitalspace.quiz.entity.Score
import com.nasinha.digitalspace.quiz.repository.QuizRepository
import kotlinx.coroutines.Dispatchers

class QuizViewModel (private val repository: QuizRepository): ViewModel() {

    //quiz questions
    val questionsList: LiveData<List<Question>> = repository.readQuestionsData().asLiveData()

    //quiz score
    fun addScore(score: Score){
        liveData(Dispatchers.IO) {
            repository.addScore(score)
            emit(true)
        }
    }

    fun readScoreData() = liveData(Dispatchers.IO) {
        emit(repository.readScoreData())
    }

    class QuizViewModelFactory(private val repository: QuizRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return QuizViewModel(repository) as T
        }
    }
}

