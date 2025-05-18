package com.tommunyiri.doggo.discover.core.di

import com.tommunyiri.doggo.discover.core.di.scope.IoDispatcher
import com.tommunyiri.doggo.discover.data.sources.local.LocalDBDataSource
import com.tommunyiri.doggo.discover.data.sources.local.LocalDBDataSourceImpl
import com.tommunyiri.doggo.discover.domain.usecases.AddFavoriteUseCase
import com.tommunyiri.doggo.discover.domain.usecases.IsFavoriteUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object FavoriteModule {
    val module =
        module {
            // viewModelOf(::HomeViewModel)
            single<LocalDBDataSource> {
                LocalDBDataSourceImpl(
                    ioDispatcher = get(IoDispatcher),
                    dogDao = get(),
                )
            }
            singleOf(::IsFavoriteUseCase)
            singleOf(::AddFavoriteUseCase)
        }
}