package com.nasinha.digitalspace.favorite.repository

import com.nasinha.digitalspace.favorite.dao.FavoriteDao
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity
import com.nasinha.digitalspace.favorite.entity.UserEntity

class FavoriteRepository(private val favoriteDao: FavoriteDao) {
    suspend fun addFavorite(favorite: FavoriteEntity) = favoriteDao.addFavorite(favorite)
    suspend fun addUserFavorite(user: UserEntity) = favoriteDao.addUserFavorite(user)
    suspend fun getUserWithFavorites(userId: String) = favoriteDao.getUserWithFavorites(userId)
    suspend fun deleteAllFavorite() = favoriteDao.deleteAllFavorite()
    suspend fun checkFavorite(image: String, userId: String) =
        favoriteDao.checkFavorite(image, userId)
    suspend fun getFavorite(image: String) = favoriteDao.getFavorite(image)
    suspend fun deleteFavoriteItem(image: String, userId: String) =
        favoriteDao.deleteFavoriteItem(image, userId)
}