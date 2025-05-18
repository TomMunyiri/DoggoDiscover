package com.tommunyiri.doggo.discover.data.sources.remote

import com.tommunyiri.doggo.discover.domain.model.NetworkDogInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApiService {
    @GET("breeds")
    suspend fun getDogs(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Response<List<NetworkDogInfo>>
}