package com.renan.digitalspace.favorite.repository

import com.renan.digitalspace.favorite.dao.FavoriteDao
import com.renan.digitalspace.favorite.entity.FavoriteEntity
import com.renan.digitalspace.favorite.model.FavoriteModel

class FavoriteRepository(private val favoriteDao: FavoriteDao) {
    suspend fun addFavorite(favorite: FavoriteEntity) = favoriteDao.addFavorite(favorite)

    suspend fun getAll() = favoriteDao.getAll()

    suspend fun updateOne(favorite: FavoriteEntity) = favoriteDao.updateOne(favorite)

    suspend fun deleteAll() = favoriteDao.deleteAll()
}