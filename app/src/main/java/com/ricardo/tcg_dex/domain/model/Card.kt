package com.ricardo.tcg_dex.domain.model

data class Card(
    val id: String,
    val localId: String,
    val name: String,
    val image: String?
)