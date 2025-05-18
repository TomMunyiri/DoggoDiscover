package com.tommunyiri.doggo.discover.data.sources.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorites_table",
    indices = [Index(value = ["id"], unique = true)],
)
data class DBDogInfo(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "bred_for")
    val bredFor: String? = null,
    @ColumnInfo(name = "breed_group")
    val breedGroup: String? = null,
    val description: String? = null,
    @ColumnInfo(name = "imperial_height")
    val imperialHeight: String,
    @ColumnInfo(name = "metric_height")
    val metricHeight: String,
    val history: String? = null,
    @ColumnInfo(name = "life_span")
    val lifeSpan: String,
    val name: String,
    val origin: String? = null,
    @ColumnInfo(name = "reference_image_id")
    val referenceImageId: String,
    val temperament: String? = null,
    @ColumnInfo(name = "imperial_weight")
    val imperialWeight: String,
    @ColumnInfo(name = "metric_weight")
    val metricWeight: String,
)
