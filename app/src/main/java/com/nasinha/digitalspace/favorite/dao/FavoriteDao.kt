package com.nasinha.digitalspace.favorite.dao

import androidx.room.*
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Insert
    suspend fun addFavorite(favorite: FavoriteEntity)

    @Query("SELECT * FROM Favorite")
    suspend fun getAll(): List<FavoriteEntity>

    @Update
    suspend fun updateOne(favorite: FavoriteEntity)

    @Query("UPDATE Favorite SET active=:active WHERE active=:activeStatus ")
    suspend fun updateActiveAll(
        active: Boolean = true,
        activeStatus: Boolean = false
    )

    @Delete
    suspend fun deleteOne(favorite: FavoriteEntity)

    @Query("DELETE FROM Favorite")
    suspend fun deleteAll()
}