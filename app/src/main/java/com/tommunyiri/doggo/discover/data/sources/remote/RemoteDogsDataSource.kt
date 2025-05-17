package com.tommunyiri.doggo.discover.data.sources.remote

import com.tommunyiri.doggo.discover.domain.model.NetworkDogInfo
import kotlinx.coroutines.flow.Flow

interface RemoteDogsDataSource {
    suspend fun getDogs(page: Int, limit: Int): Flow<List<NetworkDogInfo>>
}