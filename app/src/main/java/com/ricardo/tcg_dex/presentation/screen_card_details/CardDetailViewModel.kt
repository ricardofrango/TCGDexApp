package com.ricardo.tcg_dex.presentation.screen_card_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricardo.tcg_dex.domain.use_case.get_card.IGetCardDetailsUseCase
import com.ricardo.tcg_dex.presentation.screen_card_details.model.CardDetailsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCardDetailsUseCase: IGetCardDetailsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CardDetailUiState>(CardDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    val id: String = checkNotNull(savedStateHandle["id"])

    init {
        getCardDetails()
    }

    private fun getCardDetails() {
        viewModelScope.launch {
            _uiState.update { CardDetailUiState.Loading }

            getCardDetailsUseCase(id)
                .onSuccess {
                    onGetCardDetailsSuccess(
                        CardDetailsUiModel(
                            id = it.id,
                            image = it.image,
                            name = it.name,
                        )
                    )
                }
                .onFailure(::onGetCardDetailsFailure)
        }
    }

    private fun onGetCardDetailsSuccess(cardDetails: CardDetailsUiModel) {
        _uiState.update { CardDetailUiState.Idle(cardDetails) }
    }

    private fun onGetCardDetailsFailure(throwable: Throwable) {
        throwable.printStackTrace()
        _uiState.update { CardDetailUiState.Error }
    }
}
