package com.khidrew.task.di


import com.khidrew.domian.repos.AllDataRepository
import com.khidrew.domian.repos.NewDataRepository
import com.khidrew.domian.useCases.AddNewDataUseCase
import com.khidrew.domian.useCases.GetAllDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllDataUseCase(allDataRepository: AllDataRepository): GetAllDataUseCase {
        return GetAllDataUseCase(allDataRepository)
    }

    @Provides
    fun provideAddNewDataUseCase(addNewDataUseCase: NewDataRepository): AddNewDataUseCase {
        return AddNewDataUseCase(addNewDataUseCase)
    }

}