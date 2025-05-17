package com.tommunyiri.doggo.discover.data.repositories

import com.tommunyiri.doggo.discover.data.mappers.DogInfoListMapper
import com.tommunyiri.doggo.discover.data.sources.remote.RemoteDogsDataSource
import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.domain.repositories.DogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DogRepositoryImpl(
    private val remoteDogsDataSource: RemoteDogsDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : DogRepository {
    override suspend fun getDogs(): Flow<List<DogInfo>> = withContext(ioDispatcher) {
        val dogInfoListMapper = DogInfoListMapper()
        remoteDogsDataSource.getDogs().map {
            dogInfoListMapper.transformToDomain(it)
        }
    }
}