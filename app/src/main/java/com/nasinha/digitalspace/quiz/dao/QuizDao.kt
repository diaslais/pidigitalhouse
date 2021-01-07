package com.nasinha.digitalspace.quiz.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nasinha.digitalspace.quiz.entity.Question

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addQuestion(question: Question)


    @Query(value = "SELECT * FROM questions")
    suspend fun readQuestionsData(): List<Question>

}