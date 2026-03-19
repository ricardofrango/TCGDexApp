package com.ricardo.tcg_dex.presentation.screen_cards_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricardo.tcg_dex.domain.use_case.get_cards.IGetCardsUseCase
import com.ricardo.tcg_dex.presentation.screen_cards_list.model.CardUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class)
class CardsListViewModel @Inject constructor(
    private val getCardsListUseCase: IGetCardsUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _uiState = MutableStateFlow<CardsListUiState>(CardsListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        registerQueryChanges()
        getCards()
    }

    fun onQueryChanged(newQuery: String) {
        _query.tryEmit(newQuery)
    }

    private fun registerQueryChanges() {
        viewModelScope.launch {
            _query
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->
                    when {
                        query.isNotEmpty() -> filterCards(query)
                        else -> getCards()
                    }
                }
        }
    }

    private fun filterCards(query: String) {
        viewModelScope.launch {
            _uiState.update { CardsListUiState.Loading }

            getCardsListUseCase(query)
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
