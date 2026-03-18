package com.ricardo.tcg_dex.networking

import com.ricardo.tcg_dex.networking.model.CardBriefDto
import com.ricardo.tcg_dex.networking.model.CardDto

interface ITCGRepository {
    suspend fun getCards(): List<CardBriefDto>
    suspend fun getCardById(id: String): CardDto
}
