package com.tommunyiri.doggo.discover.core.di

import com.tommunyiri.doggo.discover.core.di.scope.IoDispatcher
import com.tommunyiri.doggo.discover.data.sources.remote.RemoteDogsDataSource
import com.tommunyiri.doggo.discover.data.sources.remote.RemoteDogsDataSourceImpl
import com.tommunyiri.doggo.discover.domain.usecases.GetDogsUseCase
import com.tommunyiri.doggo.discover.presentation.screens.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object HomeModule {
    val module =
        module {
            single { Dispatchers.IO }
            single<RemoteDogsDataSource> {
                RemoteDogsDataSourceImpl(
                    ioDispatcher = get(IoDispatcher),
                    dogsApiService = get(),
                )
            }
            viewModelOf(::HomeViewModel)
            singleOf(::GetDogsUseCase)
        }
}