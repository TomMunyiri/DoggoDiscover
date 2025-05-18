package com.tommunyiri.doggo.discover.domain.usecases

import com.tommunyiri.doggo.discover.domain.repositories.DogRepository

class RemoveFavoriteUseCase(private val dogRepository: DogRepository) {
    suspend fun invoke(id: Int) {
        dogRepository.removeFavorite(id)
    }
}