package com.tommunyiri.doggo.discover.core.di

import com.tommunyiri.doggo.discover.presentation.screens.favorites.FavoritesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object FavoriteModule {
    val module =
        module {
            viewModelOf(::FavoritesViewModel)
        }
}