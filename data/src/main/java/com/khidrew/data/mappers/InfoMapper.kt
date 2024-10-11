package com.khidrew.data.mappers

import com.khidrew.data.dataSource.local.database.databaseEntities.Info
import com.khidrew.domian.entities.InfoModel

object InfoMapper {
    fun mapInfoModelToInfoEntity(info: InfoModel): Info =
        Info(
            info.id,
            info.name,
            info.age,
            info.job,
            info.gender
        )

    fun mapInfoEntityToInfoModel(info: Info): InfoModel =
        InfoModel(
            info.id,
            info.name,
            info.age,
            info.job,
            info.gender
        )
}