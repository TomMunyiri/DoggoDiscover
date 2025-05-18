package com.tommunyiri.doggo.discover.core.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val error: Throwable) : Result<Nothing>()
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> {
            Result.Success(it)
        }
        .catch { emit(Result.Error(it)) }
}
