package com.ricardo.tcg_dex.presentation.screen_card_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ricardo.tcg_dex.presentation.TCGDexScreen
import com.ricardo.tcg_dex.presentation.screen_card_details.model.CardDetailsUiModel
import com.ricardo.tcg_dex.ui.theme.TCGDexAppTheme

fun NavGraphBuilder.cardDetailsComposable(modifier: Modifier = Modifier) =
    composable(
        route = TCGDexScreen.CardDetail.route + "?id={id}",
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) {
        CardDetailsScreen(modifier)
    }

@Composable
private fun CardDetailsScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<CardDetailViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    ShowCardDetails(
        modifier = modifier.fillMaxSize(),
        uiState = uiState,
    )
}

@Composable
private fun ShowCardDetails(
    modifier: Modifier = Modifier,
    uiState: CardDetailUiState
) {
    Box(modifier = modifier) {
        when (uiState) {
            CardDetailUiState.Loading ->
                CardDetailsLoadingState(Modifier.fillMaxSize())

            is CardDetailUiState.Idle ->
                CardDetailsIdleState(
                    modifier = Modifier.fillMaxSize(),
                    cardDetails = uiState.card,
                )

            CardDetailUiState.Error ->
                CardDetailsErrorState(Modifier.fillMaxSize())
        }
    }
}

@Composable
fun CardDetailsIdleState(
    modifier: Modifier = Modifier,
    cardDetails: CardDetailsUiModel
) {
    Column(modifier) {
        Text(cardDetails.name)
    }
}

@Composable
private fun CardDetailsLoadingState(
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
private fun CardDetailsErrorState(
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
    @PreviewParameter(CardDetailsScreenUiStatesProvider::class) uiState: CardDetailUiState
) {
    TCGDexAppTheme {
        ShowCardDetails(
            uiState = uiState,
        )
    }
}

private class CardDetailsScreenUiStatesProvider : PreviewParameterProvider<CardDetailUiState> {
    override val values: Sequence<CardDetailUiState>
        get() = sequenceOf(
            CardDetailUiState.Loading,
            CardDetailUiState.Error,
            CardDetailUiState.Idle(
                CardDetailsUiModel(
                    id = "ID",
                    name = "Name",
                    image = null
                )
            )
        )
}
