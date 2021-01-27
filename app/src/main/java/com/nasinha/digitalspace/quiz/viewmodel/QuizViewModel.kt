package com.nasinha.digitalspace.quiz.viewmodel

import androidx.lifecycle.*
import com.nasinha.digitalspace.quiz.entity.Question
import com.nasinha.digitalspace.quiz.entity.Score
import com.nasinha.digitalspace.quiz.repository.QuizRepository
import com.nasinha.digitalspace.utils.AuthUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

    //le a lista de jogos desse usuário
//    fun readScoreData(userId: String) = liveData(Dispatchers.IO) {
//        val result = repository.readScoreData(userId)
//        emit(result)
//    }

    //pega a lista de jogos desse usuario e deleta o jogo com a menor pontuação
    fun deleteMinimum(userId: String) = liveData(Dispatchers.IO) {
        val list = repository.readScoreData(userId)

        if (list.size > 5) {
            var minScore: Score? = null
            for (score in list) {
                if (minScore == null) minScore = score
                if (score.points!! < minScore.points!!) {
                    minScore = score
                }
            }
            if (minScore != null) {
                deleteScore(minScore)
            }
        }
        emit(true)
    }

    class QuizViewModelFactory(private val repository: QuizRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return QuizViewModel(repository) as T
        }
    }
}

