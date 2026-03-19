package com.ricardo.tcg_dex.domain.use_case.get_cards

import com.ricardo.tcg_dex.domain.ITCGRepository
import com.ricardo.tcg_dex.domain.model.Card
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(
    private val repository: ITCGRepository
) : IGetCardsUseCase {

    override suspend fun invoke(name: String?) =
        runCatching {
            repository.getCards(name)
                .filter { it.image != null } // Simple business rule to not show the cards without image.
        }.onFailure { Result.failure<List<Card>>(it) }

}
