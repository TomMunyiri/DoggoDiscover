package com.tommunyiri.doggo.discover.data.sources.local

import com.tommunyiri.doggo.discover.data.sources.local.entities.DBDogInfo
import kotlinx.coroutines.flow.Flow

interface LocalDBDataSource {
    suspend fun addFavorite(dbDogInfo: DBDogInfo)

    fun isFavorite(id: Int): Flow<Boolean>

    suspend fun removeFavorite(id: Int)

    fun getFavorites(): Flow<List<DBDogInfo>>
}