package com.tommunyiri.doggo.discover.core.di.scope

import org.koin.core.qualifier.named

val DefaultDispatcher = named("DefaultDispatcher")
val IoDispatcher = named("IoDispatcher")
val MainDispatcher = named("MainDispatcher")
