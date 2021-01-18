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
    suspend fun addUserFavorite(user: UserEntity)

    @Transaction
    @Query("SELECT * FROM User WHERE userId=:userId")
    suspend fun getUserWithFavorites(userId: String): List<UserWithFavorites>

    @Transaction
    @Query("SELECT COUNT(*) FROM User WHERE userId=:userId AND image=:image")
    suspend fun checkFavorite(image: String, userId: String): Int

    @Transaction
    @Query("SELECT * FROM Favorite WHERE image=:image")
    suspend fun getFavorite(image: String): FavoriteEntity

    @Transaction
    @Query("UPDATE Favorite SET titleBr = :titleBr WHERE image=:image")
    suspend fun updateTitleBr(image: String, titleBr: String)

    @Transaction
    @Query("UPDATE Favorite SET textBr = :textBr WHERE image=:image")
    suspend fun updateTextBr(image: String, textBr: String)

    @Transaction
    @Query("DELETE FROM User WHERE userId=:userId AND image=:image")
    suspend fun deleteFavoriteItem(image: String, userId: String)

    @Query("DELETE FROM Favorite")
    suspend fun deleteAllFavorite()
}