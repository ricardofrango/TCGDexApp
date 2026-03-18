package com.ricardo.tcg_dex.presentation.screen_cards_list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ricardo.tcg_dex.presentation.TCGDexScreen
import com.ricardo.tcg_dex.ui.theme.TCGDexAppTheme

fun NavGraphBuilder.cardsListComposable(
    modifier: Modifier = Modifier,
    navigate: (String) -> Unit,
) = composable(TCGDexScreen.CardList.route) {
    CardsListScreen(modifier, navigate)
}

@Composable
private fun CardsListScreen(modifier: Modifier = Modifier, navigate: (String) -> Unit) {
    Column(modifier) {
        Button(onClick = {
            navigate(TCGDexScreen.CardDetail.route)
        }) {
            Text("Navigate")
        }
    }
}

@Preview
@Composable
private fun CardsListScreenPreview() {
    TCGDexAppTheme {
        CardsListScreen(navigate = {})
    }
}
