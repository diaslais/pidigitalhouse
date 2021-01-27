package com.nasinha.digitalspace.quiz.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nasinha.digitalspace.quiz.entity.Question
import com.nasinha.digitalspace.quiz.entity.Score
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    @Query(value = "SELECT * FROM questions ORDER BY RANDOM()")
    fun readQuestionsData(): Flow<List<Question>>

    @Insert
    suspend fun addScore(score: Score)

    @Delete
    suspend fun deleteScore(score: Score)

    @Query(value = "SELECT * FROM score WHERE userId = :userId ORDER BY points ASC LIMIT 5")
    suspend fun readScoreData(userId: String): List<Score>
}