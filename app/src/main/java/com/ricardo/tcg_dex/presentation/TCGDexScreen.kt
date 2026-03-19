package com.ricardo.tcg_dex.presentation

sealed class TCGDexScreen(val route: String) {
    object CardList : TCGDexScreen("card_list_screen")
    object CardDetail : TCGDexScreen("card_detail_screen")
}
