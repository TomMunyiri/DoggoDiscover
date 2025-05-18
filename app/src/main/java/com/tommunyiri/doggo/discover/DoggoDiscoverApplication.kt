package com.tommunyiri.doggo.discover

import android.app.Application
import com.tommunyiri.doggo.discover.core.di.AppModule
import com.tommunyiri.doggo.discover.core.di.DatabaseModule
import com.tommunyiri.doggo.discover.core.di.DispatchersModule
import com.tommunyiri.doggo.discover.core.di.FavoriteModule
import com.tommunyiri.doggo.discover.core.di.HomeModule
import com.tommunyiri.doggo.discover.core.di.NetworkModule
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
                AppModule.module,
                NetworkModule.module,
                HomeModule.module,
                DispatchersModule.module,
                DatabaseModule.module,
                FavoriteModule.module
            )
        }
    }
}