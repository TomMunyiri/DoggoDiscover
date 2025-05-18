package com.tommunyiri.doggo.discover.core.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tommunyiri.doggo.discover.BuildConfig
import com.tommunyiri.doggo.discover.data.sources.remote.DogApiService
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

private val json =
    Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

object NetworkModule {
    val module =
        module {
            single {
                val chuckerCollector =
                    ChuckerCollector(
                        context = androidContext(),
                        showNotification = true,
                        retentionPeriod = RetentionManager.Period.ONE_HOUR,
                    )

                val chuckerInterceptor =
                    ChuckerInterceptor.Builder(androidContext())
                        .collector(chuckerCollector)
                        .maxContentLength(250000L)
                        .redactHeaders(emptySet())
                        .alwaysReadResponseBody(false)
                        .build()

                // Dog API authorization Interceptor
                val authInterceptor =
                    Interceptor { chain ->
                        val request =
                            chain.request()
                                .newBuilder()
                                .addHeader("x-api-key", BuildConfig.DOG_API_KEY)
                                .build()
                        chain.proceed(request)
                    }

                OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .addInterceptor(chuckerInterceptor)
                    .build()
            }
            single {
                Retrofit.Builder()
                    .addConverterFactory(
                        json.asConverterFactory(
                            contentType = "application/json".toMediaType(),
                        ),
                    )
                    .client(get())
                    .baseUrl(BuildConfig.DOG_API_BASE_URL)
                    .build()
            }
            single { get<Retrofit>().create(DogApiService::class.java) }
        }
}