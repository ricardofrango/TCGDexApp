package com.ricardo.tcg_dex.networking.api

import com.ricardo.tcg_dex.networking.model.ResponseCardBrief
import retrofit2.http.GET

internal interface TCGDexAPI {

    @GET("v2/en/cards")
    suspend fun getCards(): List<ResponseCardBrief>
}
