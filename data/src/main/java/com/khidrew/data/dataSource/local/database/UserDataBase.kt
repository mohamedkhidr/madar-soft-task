package com.khidrew.data.dataSource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.khidrew.data.dataSource.local.database.dao.InfoDao
import com.khidrew.data.dataSource.local.database.databaseEntities.Info


@Database(
    entities = [Info::class],
    version = 1
)
abstract class UserDataBase : RoomDatabase() {

    abstract fun infoDao(): InfoDao

}