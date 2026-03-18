package com.ricardo.tcg_dex.domain.use_case.get_cards

import com.ricardo.tcg_dex.domain.model.Card
import com.ricardo.tcg_dex.networking.ITCGRepository
import com.ricardo.tcg_dex.presentation.screen_cards_list.model.CardUiModel
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(
    private val repository: ITCGRepository
) : IGetCardsUseCase {

    override suspend fun invoke() = runCatching {
        repository.getCards()
            .map {
                Card(
                    id = it.id,
                    name = it.name,
                    localId = it.localId,
                    image = it.image,
                )
            }
    }.onFailure { Result.failure<List<CardUiModel>>(it) }

}
