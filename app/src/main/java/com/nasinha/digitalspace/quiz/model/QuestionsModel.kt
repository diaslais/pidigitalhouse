package com.nasinha.digitalspace.quiz.model


data class QuestionsModel(
    var id: Int,
    var question: String,
    var answer1: String,
    var answer2: String,
    var answer3: String,
    var answer4: String,
    var correctAnswer: Int
)