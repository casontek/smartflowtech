package com.carbon.smartflow.di

import android.provider.SyncStateContract
import com.carbon.smartflow.data.network.Repository
import com.carbon.smartflow.data.network.RestService
import com.carbon.smartflow.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttpClient() : OkHttpClient = OkHttpClient
        .Builder()
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.baseUrl)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun providesRestService(retrofit: Retrofit) : RestService =
        retrofit.create(RestService::class.java)

    @Singleton
    @Provides
    fun providesNetworkRepository(restService: RestService) =
        Repository(restService)

}