package com.tommunyiri.doggo.discover

import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.domain.model.NetworkDogInfo
import com.tommunyiri.doggo.discover.domain.model.NetworkHeight
import com.tommunyiri.doggo.discover.domain.model.NetworkWeight

val fakeDogInfo =
    DogInfo(
        bredFor = "test",
        breedGroup = "",
        description = "",
        imperialHeight = "12",
        metricHeight = "13",
        history = "",
        id = 123,
        lifeSpan = "12 years",
        name = "German Shepherd",
        origin = "Kenya",
        referenceImageId = "HkC31gcNm",
        temperament = "friendly",
        imperialWeight = "12-14",
        metricWeight = "13-16",
    )

val fakeNetworkDogInfo =
    NetworkDogInfo(
        bredFor = "test",
        breedGroup = "",
        description = "",
        history = "",
        id = 123,
        lifeSpan = "12 years",
        name = "German Shepherd",
        origin = "Kenya",
        referenceImageId = "HkC31gcNm",
        temperament = "friendly",
        weight = NetworkWeight(imperial = "12-14", metric = "13-16"),
        height = NetworkHeight(imperial = "12", metric = "13"),
    )

val fakeDogList =
    listOf(
        DogInfo(
            id = 1,
            name = "Labrador",
            referenceImageId = "xyz123",
            bredFor = "Retrieving",
            breedGroup = "Sporting",
            lifeSpan = "10-12 years",
            temperament = "Friendly, Active",
            origin = "Canada",
            metricHeight = "54-56",
            imperialHeight = "21-22",
            metricWeight = "25-32",
            imperialWeight = "55-71",
        ),
        DogInfo(
            id = 2,
            name = "German Shepherd",
            referenceImageId = "abc456",
            bredFor = "Herding",
            breedGroup = "Herding",
            lifeSpan = "10-13 years",
            temperament = "Loyal, Intelligent",
            origin = "Germany",
            metricHeight = "58-63",
            imperialHeight = "23-25",
            metricWeight = "22-40",
            imperialWeight = "49-88",
        ),
    )

val fakeNetworkDogList =
    listOf(
        NetworkDogInfo(
            id = 1,
            name = "Labrador",
            referenceImageId = "xyz123",
            bredFor = "Retrieving",
            breedGroup = "Sporting",
            lifeSpan = "10-12 years",
            temperament = "Friendly, Active",
            origin = "Canada",
            height = NetworkHeight(imperial = "21-22", metric = "54-56"),
            weight = NetworkWeight(imperial = "55-71", metric = "25-32"),
        ),
        NetworkDogInfo(
            id = 2,
            name = "German Shepherd",
            referenceImageId = "abc456",
            bredFor = "Herding",
            breedGroup = "Herding",
            lifeSpan = "10-13 years",
            temperament = "Loyal, Intelligent",
            origin = "Germany",
            height = NetworkHeight(imperial = "23-25", metric = "58-63"),
            weight = NetworkWeight(imperial = "49-88", metric = "22-40"),
        ),
    )