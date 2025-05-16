package com.tommunyiri.doggo.discover

import android.app.Application
import com.tommunyiri.doggo.discover.core.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DoggoDiscoverApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DoggoDiscoverApplication)
            modules(
                AppModule.module
            )
        }
    }
}