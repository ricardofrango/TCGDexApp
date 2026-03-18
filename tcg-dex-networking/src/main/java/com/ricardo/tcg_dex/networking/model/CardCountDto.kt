package com.ricardo.tcg_dex.networking.model

data class CardCountDto(
    val total: Int, // The total amount of cards in this set (including hidden)
    val official: Int, // The amount of cards in this set (displayed on the bottom left/right of the card)
)
