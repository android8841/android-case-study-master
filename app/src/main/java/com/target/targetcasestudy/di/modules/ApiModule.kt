package com.target.targetcasestudy.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.target.targetcasestudy.data.db.DatabaseHelper
import com.target.targetcasestudy.data.network.DealsAPIService
import com.target.targetcasestudy.repositories.DealsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApiModule {

    private const val BASE_URL_DEALS_SERVICE = "https://api.target.com/"

    @Singleton
    @Provides
    fun provideGSON(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL_DEALS_SERVICE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @Singleton
    @Provides
    fun provideDealsApiService(retrofit: Retrofit): DealsAPIService {
        return retrofit.create(DealsAPIService::class.java)
    }

    @Singleton
    @Provides
    fun provideDealsRepository(
        dealsApiService: DealsAPIService,
        databaseHelper: DatabaseHelper
    ): DealsRepository {
        return DealsRepository(dealsApiService, databaseHelper)
    }
}