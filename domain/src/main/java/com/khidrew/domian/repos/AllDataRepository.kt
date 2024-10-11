package com.khidrew.domian.repos

import com.khidrew.domian.entities.InfoModel

interface AllDataRepository {
    suspend fun getAllInfo():List<InfoModel>
}