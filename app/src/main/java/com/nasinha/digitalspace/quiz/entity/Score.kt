package com.nasinha.digitalspace.quiz.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score")
data class Score (
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo
    var date: String?,
    @ColumnInfo
    var points: Int?
)