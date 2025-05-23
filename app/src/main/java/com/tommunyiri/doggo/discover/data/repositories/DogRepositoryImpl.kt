package com.tommunyiri.doggo.discover.data.repositories

import com.tommunyiri.doggo.discover.data.mappers.DogInfoListMapper
import com.tommunyiri.doggo.discover.data.mappers.DogInfoMapper
import com.tommunyiri.doggo.discover.data.sources.local.LocalDBDataSource
import com.tommunyiri.doggo.discover.data.sources.remote.RemoteDogsDataSource
import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.domain.repositories.DogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DogRepositoryImpl(
    private val remoteDogsDataSource: RemoteDogsDataSource,
    private val ioDispatcher: CoroutineDispatcher,
    private val localDBDataSource: LocalDBDataSource,
) : DogRepository {
    override fun getDogs(
        page: Int,
        limit: Int,
    ): Flow<List<DogInfo>> {
        val dogInfoListMapper = DogInfoListMapper()
        return remoteDogsDataSource.getDogs(page, limit).map {
            dogInfoListMapper.transformNetworkToDomain(it)
        }
    }

    override suspend fun addFavorite(dogInfo: DogInfo) =
        withContext(ioDispatcher) {
            val dogInfoMapper = DogInfoMapper()
            localDBDataSource.addFavorite(dogInfoMapper.transformDomainToDb(dogInfo))
        }

    override fun isFavorite(id: Int): Flow<Boolean> = localDBDataSource.isFavorite(id)

    override suspend fun removeFavorite(id: Int) =
        withContext(ioDispatcher) {
            localDBDataSource.removeFavorite(id)
        }

    override fun getFavorites(): Flow<List<DogInfo>> {
        val dogInfoListMapper = DogInfoListMapper()
        return localDBDataSource.getFavorites().map { dbDogInfoList ->
            dogInfoListMapper.transformDbToDomain(dbDogInfoList)
        }
    }
}