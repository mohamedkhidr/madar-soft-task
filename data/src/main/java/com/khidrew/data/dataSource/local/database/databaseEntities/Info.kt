package com.khidrew.data.dataSource.local.database.databaseEntities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "info")
data class Info(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: Int,
    val job: String,
    val gender: String
)
