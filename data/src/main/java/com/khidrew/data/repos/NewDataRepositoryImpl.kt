package com.khidrew.data.repos

import com.khidrew.data.dataSource.local.database.UserDataBase
import com.khidrew.data.mappers.InfoMapper
import com.khidrew.domian.entities.InfoModel
import com.khidrew.domian.repos.NewDataRepository
import javax.inject.Inject

class NewDataRepositoryImpl @Inject constructor(private val dataBase: UserDataBase) :
    NewDataRepository {
    override suspend fun addNewData(info: InfoModel) {
        dataBase.infoDao().insertInfo(InfoMapper.mapInfoModelToInfoEntity(info))
    }
}