package com.tommunyiri.doggo.discover.data.sources.remote

import com.tommunyiri.doggo.discover.domain.model.NetworkDogInfo
import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds")
    suspend fun getDogs(): Response<List<NetworkDogInfo>>
}