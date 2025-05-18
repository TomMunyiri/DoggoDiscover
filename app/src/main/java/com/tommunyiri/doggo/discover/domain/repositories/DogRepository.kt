package com.tommunyiri.doggo.discover.domain.repositories

import com.tommunyiri.doggo.discover.domain.model.DogInfo
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    fun getDogs(
        page: Int,
        limit: Int,
    ): Flow<List<DogInfo>>

    suspend fun addFavorite(dogInfo: DogInfo)

    fun isFavorite(id: String): Flow<Boolean>
}