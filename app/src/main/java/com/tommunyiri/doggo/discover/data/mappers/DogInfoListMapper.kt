package com.tommunyiri.doggo.discover.data.mappers

import com.tommunyiri.doggo.discover.data.sources.local.entities.DBDogInfo
import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.domain.model.NetworkDogInfo

class DogInfoListMapper {
    fun transformNetworkToDomain(networkDogInfo: List<NetworkDogInfo>): List<DogInfo> {
        return networkDogInfo.map {
            DogInfo(
                bredFor = it.bredFor,
                breedGroup = it.breedGroup,
                description = it.description,
                metricHeight = it.height.metric,
                imperialHeight = it.height.imperial,
                history = it.history,
                id = it.id,
                lifeSpan = it.lifeSpan,
                name = it.name,
                origin = it.origin,
                referenceImageId = it.referenceImageId,
                temperament = it.temperament,
                metricWeight = it.weight.metric,
                imperialWeight = it.weight.imperial,
            )
        }
    }

    fun transformDbToDomain(dbDogInfo: List<DBDogInfo>): List<DogInfo> {
        return dbDogInfo.map {
            DogInfo(
                bredFor = it.bredFor,
                breedGroup = it.breedGroup,
                description = it.description,
                metricHeight = it.metricHeight,
                imperialHeight = it.imperialHeight,
                history = it.history,
                id = it.id,
                lifeSpan = it.lifeSpan,
                name = it.name,
                origin = it.origin,
                referenceImageId = it.referenceImageId,
                temperament = it.temperament,
                metricWeight = it.metricWeight,
                imperialWeight = it.imperialWeight,
            )
        }
    }
}