package com.tommunyiri.doggo.discover.core.di

import com.tommunyiri.doggo.discover.core.di.scope.IoDispatcher
import com.tommunyiri.doggo.discover.core.utils.NetworkHelper
import com.tommunyiri.doggo.discover.core.utils.ResourcesProvider
import com.tommunyiri.doggo.discover.data.repositories.DogRepositoryImpl
import com.tommunyiri.doggo.discover.data.sources.local.LocalDBDataSource
import com.tommunyiri.doggo.discover.data.sources.local.LocalDBDataSourceImpl
import com.tommunyiri.doggo.discover.domain.repositories.DogRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object AppModule {
    val module =
        module {
            singleOf(::ResourcesProvider)
            singleOf(::NetworkHelper)
            single<LocalDBDataSource> {
                LocalDBDataSourceImpl(
                    ioDispatcher = get(IoDispatcher),
                    dogDao = get(),
                )
            }
            single<DogRepository> {
                DogRepositoryImpl(
                    remoteDogsDataSource = get(),
                    ioDispatcher = get(IoDispatcher),
                    localDBDataSource = get(),
                )
            }
        }
}