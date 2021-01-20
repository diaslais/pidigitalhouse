package com.nasinha.digitalspace.quiz.repository

import com.nasinha.digitalspace.quiz.dao.QuizDao
import com.nasinha.digitalspace.quiz.entity.Score

class QuizRepository (private val quizDao: QuizDao) {

    suspend fun addScore(score: Score) = quizDao.addScore(score)
    fun readScoreData() = quizDao.readScoreData()
    fun readQuestionsData() = quizDao.readQuestionsData()

}