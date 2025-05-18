package com.tommunyiri.doggo.discover.data.sources.local

import com.tommunyiri.doggo.discover.data.sources.local.entities.DBDogInfo
import kotlinx.coroutines.flow.Flow

interface LocalDBDataSource {
    suspend fun addFavorite(dbDogInfo: DBDogInfo)

    fun isFavorite(id: String): Flow<Boolean>
}