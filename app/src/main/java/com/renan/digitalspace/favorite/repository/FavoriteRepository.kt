package com.renan.digitalspace.favorite.repository

import com.renan.digitalspace.favorite.model.FavoriteModel

class FavoriteRepository {
    fun getFavorites(callback: (favorites: MutableList<FavoriteModel>) -> Unit) {
        val favoritesDataSet = setFavoritesList()
        callback.invoke(favoritesDataSet)
    }

    private fun setFavoritesList(): MutableList<FavoriteModel> {
        return mutableListOf(
            FavoriteModel(
                1,
                "https://www.nasa.gov/sites/default/files/styles/image_card_4x3_ratio/public/thumbnails/image/iss064e007861.jpg",
                "Relaxing Inside the Space Station's Window to the World",
                "Dec. 3, 2020"
            ),
            FavoriteModel(
                2,
                "https://www.nasa.gov/sites/default/files/styles/image_card_4x3_ratio/public/thumbnails/image/herbig-haro-jet.jpg",
                "Awakening Newborn Stars",
                "Dec. 2, 2020"

            ), FavoriteModel(
                3,
                "https://www.nasa.gov/sites/default/files/styles/image_card_4x3_ratio/public/thumbnails/image/pia20176_main.jpg",
                "Earth May Be Surrounded by Hairy Dark Matter",
                "Dec. 1, 2020"
            )
        )
    }
}