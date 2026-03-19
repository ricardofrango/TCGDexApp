package com.ricardo.tcg_dex.presentation.screen_card_details.model

data class CardDetailsUiModel(
    val id: String,
    val name: String,
    val rarity: String?,
    val description: String?,
    private val image: String?,
) {
    val imageUrl: String?
        get() = "$image/high.webp".takeIf { image != null }
}
