package com.khidrew.data.repos

import com.khidrew.data.dataSource.local.database.UserDataBase
import com.khidrew.data.mappers.InfoMapper
import com.khidrew.domian.entities.InfoModel
import com.khidrew.domian.repos.AllDataRepository
import javax.inject.Inject

class AllDataRepositoryImpl @Inject constructor(private val dataBase: UserDataBase) :
    AllDataRepository {
    override suspend fun getAllInfo(): List<InfoModel> {
        return dataBase.infoDao().getAllInfo().map { InfoMapper.mapInfoEntityToInfoModel(it) }
    }
}