package com.walmartsample.di

import com.walmartsample.BuildConfig
import com.walmartsample.Config
import com.walmartsample.network.Authintercepter
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private const val baseUrl = Config.BASE_URL
    private const val apiKey = "acd3f3faca418969c5ce4cc941a41fc0"

    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(Authintercepter(apiKey))
                .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }
}