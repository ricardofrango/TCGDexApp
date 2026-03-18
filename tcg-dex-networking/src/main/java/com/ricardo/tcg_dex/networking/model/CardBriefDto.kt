package com.ricardo.tcg_dex.networking.model

data class CardBriefDto(
    val id: String,
    val localId: String,
    val name: String,
    val image: String?,
)
