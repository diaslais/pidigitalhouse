package com.nasinha.digitalspace.favorite.repository

import com.nasinha.digitalspace.favorite.dao.FavoriteDao
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity

class FavoriteRepository(private val favoriteDao: FavoriteDao) {
    suspend fun addFavorite(favorite: FavoriteEntity) = favoriteDao.addFavorite(favorite)
    suspend fun getAll() = favoriteDao.getAll()
    suspend fun deleteOne(favorite: FavoriteEntity) = favoriteDao.deleteOne(favorite)
    suspend fun deleteAll() = favoriteDao.deleteAll()
    suspend fun checkFavorite(image: String) = favoriteDao.checkFavorite(image)
    suspend fun deleteFavoriteItem(image: String) = favoriteDao.deleteFavoriteItem(image)
}