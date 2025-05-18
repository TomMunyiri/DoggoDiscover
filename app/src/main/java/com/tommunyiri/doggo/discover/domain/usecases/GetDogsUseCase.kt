package com.tommunyiri.doggo.discover.domain.usecases

import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.domain.repositories.DogRepository
import kotlinx.coroutines.flow.Flow

class GetDogsUseCase(private val dogRepository: DogRepository) {
    suspend fun invoke(
        page: Int,
        limit: Int,
    ): Flow<List<DogInfo>> = dogRepository.getDogs(page, limit)
}