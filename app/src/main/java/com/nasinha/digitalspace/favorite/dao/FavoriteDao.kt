package com.nasinha.digitalspace.favorite.dao

import androidx.room.*
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Insert
    suspend fun addFavorite(favorite: FavoriteEntity)

    @Query("SELECT * FROM Favorite")
    suspend fun getAll(): List<FavoriteEntity>

    @Query("SELECT COUNT(*) FROM Favorite where image=:image")
    suspend fun checkFavorite(image: String): Int

    @Query("DELETE FROM Favorite where image=:image")
    suspend fun deleteFavoriteItem(image: String)

    @Delete
    suspend fun deleteOne(favorite: FavoriteEntity)

    @Query("DELETE FROM Favorite")
    suspend fun deleteAll()
}