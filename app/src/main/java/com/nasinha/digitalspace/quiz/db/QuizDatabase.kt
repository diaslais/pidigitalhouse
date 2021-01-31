package com.nasinha.digitalspace.quiz.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nasinha.digitalspace.quiz.dao.QuizDao
import com.nasinha.digitalspace.quiz.entity.Question
import com.nasinha.digitalspace.quiz.entity.Score

@Database(
    entities = [Question::class, Score::class],
    version = 3,
    exportSchema = false
)
abstract class QuizDatabase: RoomDatabase() {

    abstract fun quizDao(): QuizDao

    companion object {
        private var INSTANCE: QuizDatabase? = null

        fun getDatabase(context: Context): QuizDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context.applicationContext, QuizDatabase::class.java,"quiz")
                    .createFromAsset("database/questions.db")
                    .build()
            }
            return INSTANCE!!
        }
    }
}