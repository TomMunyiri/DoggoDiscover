package com.tommunyiri.doggo.discover.core.di

import com.tommunyiri.doggo.discover.core.di.scope.DefaultDispatcher
import com.tommunyiri.doggo.discover.core.di.scope.IoDispatcher
import com.tommunyiri.doggo.discover.core.di.scope.MainDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

object DispatchersModule {
    val module =
        module {
            single<CoroutineDispatcher>(DefaultDispatcher) { Dispatchers.Default }
            single<CoroutineDispatcher>(IoDispatcher) { Dispatchers.IO }
            single<CoroutineDispatcher>(MainDispatcher) { Dispatchers.Main }
        }
}