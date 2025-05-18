package com.tommunyiri.doggo.discover.core.di

import com.tommunyiri.doggo.discover.core.di.scope.IoDispatcher
import com.tommunyiri.doggo.discover.data.sources.local.LocalDBDataSource
import com.tommunyiri.doggo.discover.data.sources.local.LocalDBDataSourceImpl
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
            // singleOf(::GetUnitCategoriesUseCase)
        }
}