package com.tommunyiri.doggo.discover.core.di

import androidx.room.Room
import com.tommunyiri.doggo.discover.data.sources.local.DogDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object DatabaseModule {
    val module =
        module {
            // Database
            single {
                Room.databaseBuilder(
                    androidApplication(),
                    DogDatabase::class.java,
                    DogDatabase.DATABASE_NAME,
                ).build()
            }

            // DAO
            single { get<DogDatabase>().dogDao() }
        }
}