package com.nasinha.digitalspace.quiz.repository

import com.nasinha.digitalspace.quiz.dao.QuizDao
import com.nasinha.digitalspace.quiz.entity.Question

class QuizRepository (private val quizDao: QuizDao) {

    suspend fun addQuestion(question: Question) = quizDao.addQuestion(question)
    fun readQuestionsData() = quizDao.readQuestionsData()

}