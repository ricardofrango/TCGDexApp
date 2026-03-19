package com.ricardo.tcg_dex.networking.api

import com.ricardo.tcg_dex.networking.model.CardBriefDto
import com.ricardo.tcg_dex.networking.model.CardDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface TCGDexAPI {

    @GET("v2/en/cards")
    suspend fun getCards(@Query("name") name: String? = null): List<CardBriefDto>

    @GET("v2/en/cards/{id}")
    suspend fun getCardById(@Path("id") id: String): CardDto
}
