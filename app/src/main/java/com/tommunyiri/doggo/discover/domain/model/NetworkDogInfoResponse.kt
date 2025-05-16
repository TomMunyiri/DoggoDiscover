package com.tommunyiri.doggo.discover.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName


class NetworkDogInfoResponse : ArrayList<NetworkDogInfo>()

@Keep
@Parcelize
data class NetworkDogInfo(
    @SerialName("adaptability")
    val adaptability: Int,
    @SerialName("affection_level")
    val affectionLevel: Int,
    @SerialName("alt_names")
    val altNames: String,
    @SerialName("cfa_url")
    val cfaUrl: String,
    @SerialName("child_friendly")
    val childFriendly: Int,
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("country_codes")
    val countryCodes: String,
    @SerialName("description")
    val description: String,
    @SerialName("dog_friendly")
    val dogFriendly: Int,
    @SerialName("energy_level")
    val energyLevel: Int,
    @SerialName("experimental")
    val experimental: Int,
    @SerialName("grooming")
    val grooming: Int,
    @SerialName("hairless")
    val hairless: Int,
    @SerialName("health_issues")
    val healthIssues: Int,
    @SerialName("hypoallergenic")
    val hypoallergenic: Int,
    @SerialName("id")
    val id: String,
    @SerialName("image")
    val networkDogImage: NetworkDogImage,
    @SerialName("indoor")
    val indoor: Int,
    @SerialName("intelligence")
    val intelligence: Int,
    @SerialName("lap")
    val lap: Int,
    @SerialName("life_span")
    val lifeSpan: String,
    @SerialName("name")
    val name: String,
    @SerialName("natural")
    val natural: Int,
    @SerialName("origin")
    val origin: String,
    @SerialName("rare")
    val rare: Int,
    @SerialName("reference_image_id")
    val referenceImageId: String,
    @SerialName("rex")
    val rex: Int,
    @SerialName("shedding_level")
    val sheddingLevel: Int,
    @SerialName("short_legs")
    val shortLegs: Int,
    @SerialName("social_needs")
    val socialNeeds: Int,
    @SerialName("stranger_friendly")
    val strangerFriendly: Int,
    @SerialName("suppressed_tail")
    val suppressedTail: Int,
    @SerialName("temperament")
    val temperament: String,
    @SerialName("vcahospitals_url")
    val vcahospitalsUrl: String,
    @SerialName("vetstreet_url")
    val vetstreetUrl: String,
    @SerialName("vocalisation")
    val vocalisation: Int,
    @SerialName("wikipedia_url")
    val wikipediaUrl: String
) : Parcelable

@Keep
@Parcelize
data class NetworkDogImage(
    @SerialName("height")
    val height: Int,
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int
) : Parcelable