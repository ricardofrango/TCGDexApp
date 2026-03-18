package com.ricardo.tcg_dex.presentation.screen_card_details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ricardo.tcg_dex.presentation.TCGDexScreen
import com.ricardo.tcg_dex.ui.theme.TCGDexAppTheme

fun NavGraphBuilder.cardDetailsComposable(modifier: Modifier = Modifier) =
    composable(TCGDexScreen.CardDetail.route) {
        CardDetailsScreen(modifier)
    }

@Composable
fun CardDetailsScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text("Card details")
    }
}

@Preview
@Composable
private fun CardDetailsScreenPreview() {
    TCGDexAppTheme {
        CardDetailsScreen()
    }
}
