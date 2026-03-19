package com.ricardo.tcg_dex.domain.model

data class CardDetails(
    val id: String,
    val name: String,
    val description: String?,
    val rarity: String?,
    val image: String?,
)
