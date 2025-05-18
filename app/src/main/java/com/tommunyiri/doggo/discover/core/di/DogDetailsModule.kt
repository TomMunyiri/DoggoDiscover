package com.tommunyiri.doggo.discover.core.di

import com.tommunyiri.doggo.discover.domain.usecases.AddFavoriteUseCase
import com.tommunyiri.doggo.discover.domain.usecases.IsFavoriteUseCase
import com.tommunyiri.doggo.discover.domain.usecases.RemoveFavoriteUseCase
import com.tommunyiri.doggo.discover.presentation.screens.dogdetails.DogDetailsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object DogDetailsModule {
    val module = module {
        viewModelOf(::DogDetailsViewModel)
        singleOf(::IsFavoriteUseCase)
        singleOf(::AddFavoriteUseCase)
        singleOf(::RemoveFavoriteUseCase)
    }
}