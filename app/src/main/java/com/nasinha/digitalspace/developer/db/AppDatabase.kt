package com.nasinha.digitalspace.developer.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nasinha.digitalspace.developer.dao.DeveloperDao
import com.nasinha.digitalspace.developer.entity.DeveloperEntity

@Database(
    entities = [DeveloperEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun developerDao(): DeveloperDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        // Singleton
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Developer"
                ).build()
            }
            return INSTANCE!!
        }
    }
}