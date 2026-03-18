package com.ricardo.tcg_dex.presentation.screen_cards_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ricardo.tcg_dex.presentation.TCGDexScreen
import com.ricardo.tcg_dex.presentation.screen_cards_list.model.CardUiModel
import com.ricardo.tcg_dex.ui.theme.TCGDexAppTheme

fun NavGraphBuilder.cardsListComposable(
    modifier: Modifier = Modifier,
    navigate: (String) -> Unit,
) = composable(TCGDexScreen.CardList.route) {
    CardsListScreen(modifier, navigate)
}

@Composable
private fun CardsListScreen(modifier: Modifier = Modifier, navigate: (String) -> Unit) {
    val viewModel = hiltViewModel<CardsListViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    val onCardClicked: (CardUiModel) -> Unit = { card ->
        navigate(TCGDexScreen.CardDetail.route + "?id=${card.id}")
    }

    ShowCardsList(
        modifier = modifier.fillMaxSize(),
        uiState = uiState,
        onCardClicked = onCardClicked
    )
}

@Composable
private fun ShowCardsList(
    modifier: Modifier = Modifier,
    uiState: CardsListUiState,
    onCardClicked: (CardUiModel) -> Unit
) {
    Box(modifier = modifier) {
        when (uiState) {
            CardsListUiState.Loading ->
                CardsListLoadingState(Modifier.fillMaxSize())

            is CardsListUiState.Idle ->
                CardsListIdleState(
                    modifier = Modifier.fillMaxSize(),
                    cards = uiState.cards,
                    onCardClicked = onCardClicked
                )

            CardsListUiState.Error ->
                CardsListErrorState(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun CardsListIdleState(
    modifier: Modifier = Modifier,
    cards: List<CardUiModel>,
    onCardClicked: (CardUiModel) -> Unit
) {
    Column(modifier) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Cards List",
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = cards) {
                CardItem(
                    modifier = Modifier.fillMaxWidth(),
                    card = it,
                    onCardClicked = onCardClicked
                )
            }
        }
    }
}

@Composable
private fun CardItem(
    modifier: Modifier = Modifier,
    card: CardUiModel,
    onCardClicked: (CardUiModel) -> Unit,
) {
    Column(modifier = modifier.clickable(onClick = { onCardClicked(card) })) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = card.name,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun CardsListLoadingState(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun CardsListErrorState(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Error",
            color = Color.Red,
        )
    }
}

@Preview
@Composable
private fun CardsListScreenPreview(
    @PreviewParameter(CardsListScreenUiStatesProvider::class) uiState: CardsListUiState
) {
    TCGDexAppTheme {
        ShowCardsList(
            uiState = uiState,
            onCardClicked = {}
        )
    }
}

private class CardsListScreenUiStatesProvider : PreviewParameterProvider<CardsListUiState> {
    override val values: Sequence<CardsListUiState>
        get() = sequenceOf(
            CardsListUiState.Loading,
            CardsListUiState.Error,
            CardsListUiState.Idle(
                listOf(
                    CardUiModel(
                        id = "ID",
                        localId = "LocalId",
                        name = "Name",
                        image = null
                    )
                )
            )
        )
}
