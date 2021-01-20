package com.nasinha.digitalspace.quiz.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nasinha.digitalspace.quiz.entity.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addQuestion(question: Question)


    @Query(value = "SELECT * FROM questions ORDER BY RANDOM()")
    fun readQuestionsData(): Flow<List<Question>>

}