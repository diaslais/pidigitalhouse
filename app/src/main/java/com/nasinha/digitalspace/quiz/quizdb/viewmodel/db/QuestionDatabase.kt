package com.nasinha.digitalspace.quiz.quizdb.viewmodel.db

//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.nasinha.digitalspace.quiz.quizdb.viewmodel.dao.QuestionsDao
//import com.nasinha.digitalspace.quiz.quizdb.viewmodel.model.QuestionEntity

//
//@Database(
//    entities = [QuestionEntity::class],
//    version = 1,
//    exportSchema = false
//)
//
//abstract class QuestionDatabase: RoomDatabase() {
//
//    abstract fun questionDao(): QuestionsDao
//
//    companion object {
//        fun getDatabase(context: Context): QuestionDatabase {
//            val instanciaQuizDb = Room.databaseBuilder(
//                context.applicationContext,
//                QuestionDatabase::class.java,
//                "questions_database"
//            ).build()
//
//            return instanciaQuizDb
//        }
//    }
//}