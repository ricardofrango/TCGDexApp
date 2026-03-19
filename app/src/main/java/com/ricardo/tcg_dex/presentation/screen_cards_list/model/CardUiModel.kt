package com.ricardo.tcg_dex.presentation.screen_cards_list.model

data class CardUiModel(
    val id: String,
    val localId: String,
    val name: String,
    private val image: String?
) {
    val imageUrl: String?
        get() = "$image/low.webp".takeIf { image != null }
}
