package com.nasinha.digitalspace.quiz.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nasinha.digitalspace.quiz.entity.Question
import com.nasinha.digitalspace.quiz.entity.Score
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Query(value = "SELECT * FROM questions ORDER BY RANDOM()")
    fun readQuestionsData(): Flow<List<Question>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addScore(score: Score)

    @Query(value = "SELECT * FROM score ORDER BY points ASC LIMIT 5")
    fun readScoreData(): Flow<List<Score>>

}