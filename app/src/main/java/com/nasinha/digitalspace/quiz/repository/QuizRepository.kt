package com.nasinha.digitalspace.quiz.repository

import com.nasinha.digitalspace.quiz.dao.QuizDao
import com.nasinha.digitalspace.quiz.entity.Score

class QuizRepository (private val quizDao: QuizDao) {

    fun readQuestionsData() = quizDao.readQuestionsData()

    suspend fun addScore(score: Score) = quizDao.addScore(score)

    suspend fun deleteScore(score: Score) = quizDao.deleteScore(score)

    suspend fun readScoreData(userId: String) = quizDao.readScoreData(userId)

}