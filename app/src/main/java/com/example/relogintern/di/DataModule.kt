package com.example.relogintern.di

import android.content.Context
import com.example.relogintern.data.api.AuthService
import com.example.relogintern.data.datasource.AuthNetworkDataSource
import com.example.relogintern.data.datasource.AuthNetworkDataSourceImpl
import com.example.relogintern.data.datasource.AuthRepository
import com.example.relogintern.data.localStorage.SharedPreferencesManager
import com.example.relogintern.data.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    // Base url
    @Provides
    fun provideBaseUrl(): String {
        return "http://api.ozinshe.com/"
    }


    // Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    // Auth request flow: repository -> datasource -> service
    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthNetworkDataSourceImpl(authService: AuthService): AuthNetworkDataSource {
        return AuthNetworkDataSourceImpl(authService)
    }

    @Provides
    @Singleton
    fun provideAuthRepositoryImpl(authNetworkDataSourceImpl: AuthNetworkDataSourceImpl): AuthRepository {
        return AuthRepositoryImpl(authNetworkDataSourceImpl)
    }


    // Shared preferences
    @Provides
    @Singleton
    fun provideSharedPreferencesManager(@ApplicationContext context: Context): SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }

}