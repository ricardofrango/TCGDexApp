package com.ricardo.tcg_dex.networking.model

data class VariantDto(
    val normal: Boolean,        // ✅ Standard non-foil version available
    val reverse: Boolean,       // ✅ Reverse holofoil version available
    val holo: Boolean,          // ✅ Holofoil version available
    val firstEdition: Boolean,  // ✅ First edition printing available
)