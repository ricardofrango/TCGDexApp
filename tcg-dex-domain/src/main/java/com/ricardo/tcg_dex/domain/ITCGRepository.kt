package com.ricardo.tcg_dex.domain

import com.ricardo.tcg_dex.domain.model.Card
import com.ricardo.tcg_dex.domain.model.CardDetails

interface ITCGRepository {
    suspend fun getCards(name: String? = null): List<Card>
    suspend fun getCardById(id: String): CardDetails
}