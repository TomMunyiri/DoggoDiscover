package com.tommunyiri.doggo.discover.data.sources.remote

import com.tommunyiri.doggo.discover.core.ApiException
import com.tommunyiri.doggo.discover.domain.model.NetworkDogInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDogsDataSourceImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val dogsApiService: DogApiService,
) : RemoteDogsDataSource {
    override fun getDogs(
        page: Int,
        limit: Int,
    ): Flow<List<NetworkDogInfo>> =
        flow {
            val response = dogsApiService.getDogs(page, limit)
            if (response.isSuccessful) {
                response.body()?.let { networkDogInfoList ->
                    emit(networkDogInfoList)
                } ?: emit(emptyList())
            } else {
                // Handle error case
                throw ApiException(statusCode = response.code(), statusMessage = response.message())
            }
        }.flowOn(ioDispatcher)
}