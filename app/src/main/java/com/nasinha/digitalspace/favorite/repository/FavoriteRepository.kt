package com.nasinha.digitalspace.favorite.repository

import com.nasinha.digitalspace.favorite.dao.FavoriteDao
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity

class FavoriteRepository(private val favoriteDao: FavoriteDao) {
    suspend fun addFavorite(favorite: FavoriteEntity) = favoriteDao.addFavorite(favorite)
    suspend fun getAll() = favoriteDao.getAll()
    suspend fun deleteOne(favorite: FavoriteEntity) = favoriteDao.deleteOne(favorite)
    suspend fun deleteAll() = favoriteDao.deleteAll()
    fun setFavorites(callback: (favorites: List<FavoriteEntity>) -> Unit) {
        val favoritesDataSet = setFavoritesList()
        callback.invoke(favoritesDataSet)
    }

    private fun setFavoritesList(): List<FavoriteEntity> {
        return listOf(
            FavoriteEntity(
                0,
                "https://www.nasa.gov/sites/default/files/styles/image_card_4x3_ratio/public/thumbnails/image/iss064e007861.jpg",
                "Relaxing Inside the Space Station's Window to the World",
                "2020-12-03",
                true
            ),
            FavoriteEntity(
                0,
                "https://www.nasa.gov/sites/default/files/styles/image_card_4x3_ratio/public/thumbnails/image/herbig-haro-jet.jpg",
                "Awakening Newborn Stars",
                "2020-12-02",
                true
            ),
            FavoriteEntity(
                0,
                "https://www.nasa.gov/sites/default/files/styles/image_card_4x3_ratio/public/thumbnails/image/pia20176_main.jpg",
                "Earth May Be Surrounded by Hairy Dark Matter",
                "2020-12-01",
                true
            ),
            FavoriteEntity(
                0,
                "https://www.nasa.gov/sites/default/files/styles/image_card_4x3_ratio/public/thumbnails/image/iss064e007861.jpg",
                "Relaxing Inside the Space Station's Window to the World",
                "2020-12-03",
                true

            ),
            FavoriteEntity(
                0,
                "https://www.nasa.gov/sites/default/files/styles/image_card_4x3_ratio/public/thumbnails/image/herbig-haro-jet.jpg",
                "Awakening Newborn Stars",
                "2020-12-02",
                true
            ),
            FavoriteEntity(
                0,
                "https://www.nasa.gov/sites/default/files/styles/image_card_4x3_ratio/public/thumbnails/image/pia20176_main.jpg",
                "Earth May Be Surrounded by Hairy Dark Matter",
                "2020-12-01",
                true
            )
        )
    }
}