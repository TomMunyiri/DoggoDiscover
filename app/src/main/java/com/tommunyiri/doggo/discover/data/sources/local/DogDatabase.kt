package com.tommunyiri.doggo.discover.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tommunyiri.doggo.discover.data.sources.local.dao.DogDao
import com.tommunyiri.doggo.discover.data.sources.local.entities.DBDogInfo

@Database(
    entities = [DBDogInfo::class],
    version = 1,
    exportSchema = true,
    // autoMigrations = [AutoMigration(from = 1, to = 2)],
)
abstract class DogDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao

    companion object {
        const val DATABASE_NAME = "dog_database"
    }
}