package com.ricardo.tcg_dex.networking

import com.ricardo.tcg_dex.networking.model.ResponseCardBrief

interface ITCGRepository {
    suspend fun getCards(): List<ResponseCardBrief>
}
