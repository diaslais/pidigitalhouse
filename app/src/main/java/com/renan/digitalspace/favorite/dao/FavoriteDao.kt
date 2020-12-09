package com.renan.digitalspace.favorite.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.renan.digitalspace.favorite.entity.FavoriteEntity

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

    @Query("DELETE FROM Favorite")
    suspend fun deleteAll()
}