package com.tommunyiri.doggo.discover.domain.repositories

import com.tommunyiri.doggo.discover.domain.model.DogInfo
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    suspend fun getDogs(): Flow<List<DogInfo>>
}