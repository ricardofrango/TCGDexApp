package com.ricardo.tcg_dex.presentation.screen_cards_list

import com.ricardo.tcg_dex.presentation.screen_cards_list.model.CardUiModel

sealed class CardsListUiState {
    object Loading: CardsListUiState()
    data class Idle(val cards: List<CardUiModel>): CardsListUiState()
    object Error: CardsListUiState()
}
