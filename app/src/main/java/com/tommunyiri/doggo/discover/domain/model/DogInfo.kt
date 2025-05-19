package com.tommunyiri.doggo.discover.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

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

@Parcelize
data class Height(
    val imperial: String,
    val metric: String,
) : Parcelable

@Parcelize
data class Weight(
    val imperial: String,
    val metric: String,
) : Parcelable