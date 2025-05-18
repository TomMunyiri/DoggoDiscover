package com.tommunyiri.doggo.discover.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Keep
@Parcelize
data class DogInfo(
    val bredFor: String? = null,
    val breedGroup: String? = null,
    val description: String? = null,
    val imperialHeight: String,
    val metricHeight: String,
    val history: String? = null,
    val id: Int,
    val lifeSpan: String,
    val name: String,
    val origin: String? = null,
    val referenceImageId: String,
    val temperament: String? = null,
    val imperialWeight: String,
    val metricWeight: String,
) : Parcelable

@Keep
@Parcelize
data class Height(
    val imperial: String,
    val metric: String,
) : Parcelable

@Keep
@Parcelize
data class Weight(
    @SerialName("imperial")
    val imperial: String,
    @SerialName("metric")
    val metric: String,
) : Parcelable