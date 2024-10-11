package com.khidrew.data.dataSource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khidrew.data.dataSource.local.database.databaseEntities.Info


@Dao
interface InfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfo(info: List<Info>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfo(info: Info)

    @Query("SELECT * FROM info")
    fun getAllInfo(): List<Info>

}