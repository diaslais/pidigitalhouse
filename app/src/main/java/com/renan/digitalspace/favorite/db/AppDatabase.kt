package com.renan.digitalspace.favorite.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.renan.digitalspace.favorite.dao.FavoriteDao
import com.renan.digitalspace.favorite.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        //        Singleton
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Favorite"
                ).build()
            }
            return INSTANCE!!
        }
    }
}