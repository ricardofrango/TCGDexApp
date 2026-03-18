package com.ricardo.tcg_dex.domain.use_case.get_cards

import com.ricardo.tcg_dex.domain.model.Card

interface IGetCardsUseCase {
    suspend operator fun invoke(): Result<List<Card>>
}
