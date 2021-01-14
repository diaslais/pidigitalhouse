package com.nasinha.digitalspace.quiz.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question (
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo
    var question: String?,
    @ColumnInfo
    var optionOne: String?,
    @ColumnInfo
    var optionTwo: String?,
    @ColumnInfo
    var optionThree: String?,
    @ColumnInfo
    var optionFour: String?,
    @ColumnInfo
    var correctAnswer: Int?
)