package com.tommunyiri.doggo.discover.domain.model


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

class NetworkDogInfoResponse : ArrayList<NetworkDogInfo>()

@Keep
@Parcelize
data class NetworkDogInfo(
    @SerialName("bred_for")
    val bredFor: String,
    @SerialName("breed_group")
    val breedGroup: String,
    @SerialName("description")
    val description: String,
    @SerialName("height")
    val height: NetworkHeight,
    @SerialName("history")
    val history: String,
    @SerialName("id")
    val id: Int,
    @SerialName("life_span")
    val lifeSpan: String,
    @SerialName("name")
    val name: String,
    @SerialName("origin")
    val origin: String,
    @SerialName("reference_image_id")
    val referenceImageId: String,
    @SerialName("temperament")
    val temperament: String,
    @SerialName("weight")
    val weight: NetworkWeight
) : Parcelable

@Keep
@Parcelize
data class NetworkHeight(
    @SerialName("imperial")
    val imperial: String,
    @SerialName("metric")
    val metric: String
) : Parcelable

@Keep
@Parcelize
data class NetworkWeight(
    @SerialName("imperial")
    val imperial: String,
    @SerialName("metric")
    val metric: String
) : Parcelable