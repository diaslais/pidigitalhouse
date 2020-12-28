package com.nasinha.digitalspace.favorite.repository

import com.nasinha.digitalspace.favorite.dao.FavoriteDao
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity

class FavoriteRepository(private val favoriteDao: FavoriteDao) {
    suspend fun addFavorite(favorite: FavoriteEntity) = favoriteDao.addFavorite(favorite)
    suspend fun getAll(userId: String) = favoriteDao.getAll(userId)
    suspend fun deleteOne(favorite: FavoriteEntity) = favoriteDao.deleteOne(favorite)
    suspend fun deleteAll() = favoriteDao.deleteAll()
    suspend fun checkFavorite(image: String, userId: String) =
        favoriteDao.checkFavorite(image, userId)

    suspend fun deleteFavoriteItem(image: String, userId: String) =
        favoriteDao.deleteFavoriteItem(image, userId)
}