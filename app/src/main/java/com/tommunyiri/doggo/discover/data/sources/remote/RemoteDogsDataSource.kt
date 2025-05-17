package com.tommunyiri.doggo.discover.data.sources.remote

import com.tommunyiri.doggo.discover.domain.model.NetworkDogInfo
import kotlinx.coroutines.flow.Flow

interface RemoteDogsDataSource {
    suspend fun getDogs(): Flow<List<NetworkDogInfo>>
}