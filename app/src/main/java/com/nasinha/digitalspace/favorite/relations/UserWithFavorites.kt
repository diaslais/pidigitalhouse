package com.nasinha.digitalspace.favorite.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity
import com.nasinha.digitalspace.favorite.entity.UserEntity

data class UserWithFavorites(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "image",
        entityColumn = "image"
    )
    val favorites: List<FavoriteEntity>
)
