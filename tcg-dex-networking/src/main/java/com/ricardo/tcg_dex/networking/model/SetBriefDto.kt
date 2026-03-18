package com.ricardo.tcg_dex.networking.model

data class SetBriefDto(
    val id: String,                 // ✅ Set Unique ID
    val name: String,               // ✅ Set Name
    val logo: String?,              // ❌ Asset, Set logo (you can add .(webp|png|jpg) to customize the format)
    val symbol: String?,            // ❌ Asset, Set Symbol (you can add .(webp|png|jpg) to customize the format
    val cardCount: CardCountDto,    // ✅ Contain information about the number of cards in the set
)