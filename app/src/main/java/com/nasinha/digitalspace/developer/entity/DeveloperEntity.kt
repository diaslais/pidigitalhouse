package com.nasinha.digitalspace.developer.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Developer")
data class DeveloperEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo
    var image: String,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var email: String,
    @ColumnInfo
    var bio: String,
    @ColumnInfo
    var linkedin: String,
    @ColumnInfo
    var github: String
)