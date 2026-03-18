package com.ricardo.tcg_dex.presentation.screen_cards_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricardo.tcg_dex.domain.use_case.get_cards.IGetCardsUseCase
import com.ricardo.tcg_dex.presentation.screen_cards_list.model.CardUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsListViewModel @Inject constructor(
    private val getCardsListUseCase: IGetCardsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CardsListUiState>(CardsListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getCards()
    }

    private fun getCards() {
        viewModelScope.launch {
            _uiState.update { CardsListUiState.Loading }

            getCardsListUseCase()
                .onFailure(::showError)
                .onSuccess { cards ->
                    showCards(
                        cards = cards.map {
                            CardUiModel(
                                id = it.id,
                                name = it.name,
                                image = it.image,
                                localId = it.localId,
                            )
                        }
                    )
                }
        }
    }

    private fun showCards(cards: List<CardUiModel>) {
        _uiState.update { CardsListUiState.Idle(cards) }
    }

    private fun showError(throwable: Throwable) {
        throwable.printStackTrace()
        _uiState.update { CardsListUiState.Error }
    }
}
