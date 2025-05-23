package com.tommunyiri.doggo.discover.data.sources.local

import com.tommunyiri.doggo.discover.data.sources.local.dao.DogDao
import com.tommunyiri.doggo.discover.data.sources.local.entities.DBDogInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDBDataSourceImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val dogDao: DogDao,
) : LocalDBDataSource {
    override suspend fun addFavorite(dbDogInfo: DBDogInfo) =
        withContext(ioDispatcher) {
            dogDao.addFavorite(dbDogInfo)
        }

    override fun isFavorite(id: Int): Flow<Boolean> = dogDao.isFavorite(id)

    override suspend fun removeFavorite(id: Int) =
        withContext(ioDispatcher) {
            dogDao.removeFavorite(id)
        }

    override fun getFavorites(): Flow<List<DBDogInfo>> = dogDao.getFavorites()
}