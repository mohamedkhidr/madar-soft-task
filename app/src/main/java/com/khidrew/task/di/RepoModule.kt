package com.khidrew.task.di


import com.khidrew.data.dataSource.local.database.UserDataBase
import com.khidrew.data.repos.AllDataRepositoryImpl
import com.khidrew.data.repos.NewDataRepositoryImpl
import com.khidrew.domian.repos.AllDataRepository
import com.khidrew.domian.repos.NewDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun getNewDataRepo(dataBase: UserDataBase): NewDataRepository {
        return NewDataRepositoryImpl(dataBase)
    }

    @Provides
    fun getAllDataRepo(dataBase: UserDataBase): AllDataRepository {
        return AllDataRepositoryImpl(dataBase)
    }
}