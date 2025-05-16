package com.tommunyiri.doggo.discover.core.di

import com.tommunyiri.doggo.discover.core.utils.NetworkHelper
import com.tommunyiri.doggo.discover.core.utils.ResourcesProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object AppModule {
    val module =
        module {
            singleOf(::ResourcesProvider)
            singleOf(::NetworkHelper)
        }
}