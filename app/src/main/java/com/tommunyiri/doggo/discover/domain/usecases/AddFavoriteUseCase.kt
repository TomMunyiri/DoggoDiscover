package com.tommunyiri.doggo.discover.domain.usecases

import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.domain.repositories.DogRepository

class AddFavoriteUseCase(private val dogRepository: DogRepository) {
    suspend fun invoke(dogInfo: DogInfo) {
        dogRepository.addFavorite(dogInfo)
    }
}