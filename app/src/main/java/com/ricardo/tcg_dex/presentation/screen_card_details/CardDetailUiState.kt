package com.ricardo.tcg_dex.presentation.screen_card_details

import com.ricardo.tcg_dex.presentation.screen_card_details.model.CardDetailsUiModel

sealed class CardDetailUiState {
    object Loading : CardDetailUiState()
    data class Idle(val card: CardDetailsUiModel) : CardDetailUiState()
    object Error : CardDetailUiState()
}
