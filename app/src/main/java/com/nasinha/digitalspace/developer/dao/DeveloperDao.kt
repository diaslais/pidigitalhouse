package com.nasinha.digitalspace.developer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nasinha.digitalspace.developer.entity.DeveloperEntity

@Dao
interface DeveloperDao {
    @Insert
    suspend fun addDeveloper(developer: DeveloperEntity)

    @Query("SELECT * FROM Developer")
    suspend fun getAll(): List<DeveloperEntity>

    @Query("DELETE FROM Developer")
    suspend fun deleteAll()
}