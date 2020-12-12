package com.nasinha.digitalspace.favorite.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo
    var image: String,
    @ColumnInfo
    var title: String?,
    @ColumnInfo
    var date: String,
    @ColumnInfo
    var active: Boolean
)