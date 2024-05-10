package com.cinexperto.app.core.di

import com.cinexperto.app.core.constants.Constants
import com.cinexperto.app.features.auth.data.network.AuthApiClient
import com.cinexperto.app.features.auth.data.repositories.AuthRepositoryImpl
import com.cinexperto.app.features.auth.domain.repositories.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthApiClient(retrofit: Retrofit) : AuthApiClient {
        return retrofit.create(AuthApiClient::class.java)
    }


}