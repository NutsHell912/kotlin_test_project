package com.sample.nutshell.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sample.nutshell.BuildConfig
import com.sample.nutshell.data.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .create()

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideApiService(httpClient: OkHttpClient, gson: Gson): ApiService {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService::class.java)
    }

}