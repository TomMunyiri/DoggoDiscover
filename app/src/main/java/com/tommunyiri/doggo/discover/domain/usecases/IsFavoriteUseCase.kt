package com.tommunyiri.doggo.discover.domain.usecases

import com.tommunyiri.doggo.discover.domain.repositories.DogRepository
import kotlinx.coroutines.flow.Flow

class IsFavoriteUseCase(private val dogRepository: DogRepository) {
    fun invoke(id: Int): Flow<Boolean> = dogRepository.isFavorite(id)
}