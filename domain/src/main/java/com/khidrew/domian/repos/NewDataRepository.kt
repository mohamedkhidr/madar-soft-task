package com.khidrew.domian.repos

import com.khidrew.domian.entities.InfoModel

interface NewDataRepository {

    suspend fun addNewData(info: InfoModel)
}