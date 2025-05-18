package com.tommunyiri.doggo.discover.data.mappers

import com.tommunyiri.doggo.discover.data.sources.local.entities.DBDogInfo
import com.tommunyiri.doggo.discover.domain.model.DogInfo

class DogInfoMapper {
    fun transformDomainToDb(dogInfo: DogInfo): DBDogInfo {
        return DBDogInfo(
            bredFor = dogInfo.bredFor,
            breedGroup = dogInfo.breedGroup,
            description = dogInfo.description,
            metricHeight = dogInfo.metricHeight,
            imperialHeight = dogInfo.imperialHeight,
            history = dogInfo.history,
            id = dogInfo.id,
            lifeSpan = dogInfo.lifeSpan,
            name = dogInfo.name,
            origin = dogInfo.origin,
            referenceImageId = dogInfo.referenceImageId,
            temperament = dogInfo.temperament,
            metricWeight = dogInfo.metricWeight,
            imperialWeight = dogInfo.imperialWeight,
        )
    }
}