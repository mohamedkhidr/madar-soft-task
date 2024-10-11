package com.khidrew.task.di

import android.content.Context
import androidx.room.Room

import com.khidrew.data.dataSource.local.database.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun getDatabaseInstance(@ApplicationContext context: Context): UserDataBase {
        return Room.databaseBuilder(context, UserDataBase::class.java, "user")
            .fallbackToDestructiveMigration()
            .build()

    }


}