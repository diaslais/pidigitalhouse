package com.nasinha.digitalspace.favorite.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "User", primaryKeys = ["image", "userId"])
data class UserEntity(
    @ColumnInfo
    var image: String,
    @ColumnInfo
    var userId: String
)