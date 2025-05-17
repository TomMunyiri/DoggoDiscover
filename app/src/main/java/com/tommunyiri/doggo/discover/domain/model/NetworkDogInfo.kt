package com.tommunyiri.doggo.discover.domain.model

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class NetworkDogInfo(
    @SerialName("bred_for")
    val bredFor: String? = null,
    @SerialName("breed_group")
    val breedGroup: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("height")
    val height: NetworkHeight,
    @SerialName("history")
    val history: String? = null,
    @SerialName("id")
    val id: Int,
    @SerialName("life_span")
    val lifeSpan: String,
    @SerialName("name")
    val name: String,
    @SerialName("origin")
    val origin: String? = null,
    @SerialName("reference_image_id")
    val referenceImageId: String,
    @SerialName("temperament")
    val temperament: String? = null,
    @SerialName("weight")
    val weight: NetworkWeight
)

@Keep
@Serializable
data class NetworkHeight(
    @SerialName("imperial")
    val imperial: String,
    @SerialName("metric")
    val metric: String
)

@Keep
@Serializable
data class NetworkWeight(
    @SerialName("imperial")
    val imperial: String,
    @SerialName("metric")
    val metric: String
)