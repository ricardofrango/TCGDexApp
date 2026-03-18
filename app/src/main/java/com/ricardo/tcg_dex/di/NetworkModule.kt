package com.ricardo.tcg_dex.di

import com.ricardo.tcg_dex.BuildConfig
import com.ricardo.tcg_dex.networking.ITCGRepository
import com.ricardo.tcg_dex.networking.TCGRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideTCGRepository(): ITCGRepository {
        return TCGRepository(BuildConfig.BASE_URL)
    }
}
