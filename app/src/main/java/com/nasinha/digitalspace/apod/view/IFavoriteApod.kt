package com.nasinha.digitalspace.apod.view

import com.nasinha.digitalspace.favorite.entity.FavoriteEntity

interface IFavoriteApod {
    fun iFavoriteApodcheck(favorite: FavoriteEntity)
    fun iFavoriteApodAdd(favorite: FavoriteEntity)
    fun iFavoriteApodRemove(favorite: FavoriteEntity)
}