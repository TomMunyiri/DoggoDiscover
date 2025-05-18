package com.tommunyiri.doggo.discover.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tommunyiri.doggo.discover.data.sources.local.entities.DBDogInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(dbDogInfo: DBDogInfo)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites_table WHERE id = :id)")
    fun isFavorite(id: Int): Flow<Boolean>

    @Query("DELETE FROM favorites_table WHERE id = :id")
    suspend fun removeFavorite(id: Int)
}