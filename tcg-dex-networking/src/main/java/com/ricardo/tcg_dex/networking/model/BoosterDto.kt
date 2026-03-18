package com.ricardo.tcg_dex.networking.model

data class BoosterDto(
    val id: String,             // ✅ Unique booster identifier
    val name: String,           // ✅ Localized booster name
    val logo: String?,          // ❌ Booster pack logo image
    val artwork_front: String?, // ❌ Front artwork image
    val artwork_back: String?,  // ❌ Back artwork image
)