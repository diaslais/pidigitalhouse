package com.nasinha.digitalspace.favorite.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite")
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo
    var image: String,
    @ColumnInfo
    var title: String?,
    @ColumnInfo
    var titleBr: String?,
    @ColumnInfo
    var text: String?,
    @ColumnInfo
    var textBr: String?,
    @ColumnInfo
    var date: String,
    @ColumnInfo
    var type: String
)