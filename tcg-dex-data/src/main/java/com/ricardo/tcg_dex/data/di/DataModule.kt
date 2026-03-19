package com.ricardo.tcg_dex.data.di

import com.ricardo.tcg_dex.data.repository.TCGRepository
import com.ricardo.tcg_dex.domain.ITCGRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindTCGRepository(repository: TCGRepository): ITCGRepository
}
