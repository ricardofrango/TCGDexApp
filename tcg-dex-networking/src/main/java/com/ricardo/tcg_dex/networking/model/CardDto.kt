package com.ricardo.tcg_dex.networking.model

data class CardDto(
    val id: String,                     // ✅ Unique identifier for the card (e.g., “swsh3-136”)
    val localId: String,                // ✅ Card number within its set
    val name: String,                   // ✅ Official card name
    val image: String?,                 // ❌ Card image URL (see Assets)
    val category: String,               // ✅ Card type: “Pokemon”, “Energy”, or “Trainer”
    val illustrator: String?,           // ❌ Artist who illustrated the card
    val rarity: String?,                // ❌ Card rarity (Common, Uncommon, Rare, etc.)
    val set: SetBriefDto,               // ✅ Set information (see Sets API)
    val variants: VariantDto,           // ✅ Available card variants
    val boosters: List<BoosterDto>?,    // ❌ Booster packs containing this card (null if available in all boosters)
    val updated: String,                // ✅ Indicate when is the last time the card data was updated (excluding pricing infos)
)