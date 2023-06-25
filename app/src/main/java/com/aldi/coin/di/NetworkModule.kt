package com.aldi.coin.di

import com.aldi.coin.BuildConfig
import com.aldi.coin.Constants.DATE_PATTERN
import com.aldi.coin.Constants.TIMEOUT_IN_SEC
import com.aldi.coin.data.network.ApiLogger
import com.aldi.coin.data.network.HeaderInterceptor
import com.aldi.coin.data.service.CoinCapApi
import com.google.gson.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor,
        logger: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(logger)
            .connectTimeout(TIMEOUT_IN_SEC.toLong(), SECONDS)
            .readTimeout(TIMEOUT_IN_SEC.toLong(), SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .setDateFormat(DATE_PATTERN)
            .create()
    }

    @Provides
    @Singleton
    fun provideHttpLogger(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor(ApiLogger())
        if (BuildConfig.DEBUG) {
            logging.level = Level.BODY
        }
        return logging
    }

    @Provides
    @Singleton
    fun provideCoinCapApi(retrofit: Retrofit): CoinCapApi {
        return CoinCapApi.Factory.createService(retrofit)
    }
}