package com.nasinha.digitalspace.favorite.dao

import androidx.room.*
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity
import com.nasinha.digitalspace.favorite.entity.UserEntity
import com.nasinha.digitalspace.favorite.relations.UserWithFavorites

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserFavorite(user:UserEntity)

    @Transaction
    @Query("SELECT * FROM User WHERE userId=:userId")
    suspend fun getUserWithFavorites(userId: String): List<UserWithFavorites>

    @Transaction
    @Query("SELECT COUNT(*) FROM User where userId=:userId AND image=:image")
    suspend fun checkFavorite(image: String, userId: String): Int

    @Transaction
    @Query("DELETE FROM User where userId=:userId AND image=:image")
    suspend fun deleteFavoriteItem(image: String, userId: String)

    @Query("DELETE FROM Favorite")
    suspend fun deleteAllFavorite()
}