package com.ricardo.tcg_dex.domain.di

import com.ricardo.tcg_dex.domain.use_case.get_card.GetCardDetailsUseCase
import com.ricardo.tcg_dex.domain.use_case.get_card.IGetCardDetailsUseCase
import com.ricardo.tcg_dex.domain.use_case.get_cards.GetCardsUseCase
import com.ricardo.tcg_dex.domain.use_case.get_cards.IGetCardsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindGetCardsUseCase(useCase: GetCardsUseCase): IGetCardsUseCase

    @Binds
    fun bindGetCardDetailsUseCase(useCase: GetCardDetailsUseCase): IGetCardDetailsUseCase
}
