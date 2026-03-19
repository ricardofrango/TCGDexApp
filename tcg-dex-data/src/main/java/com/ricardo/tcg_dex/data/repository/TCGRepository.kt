package com.ricardo.tcg_dex.data.repository

import com.ricardo.tcg_dex.domain.ITCGRepository
import com.ricardo.tcg_dex.domain.model.Card
import com.ricardo.tcg_dex.domain.model.CardDetails
import com.ricardo.tcg_dex.networking.api.TCGDexAPI
import javax.inject.Inject

class TCGRepository @Inject constructor(
    private val api: TCGDexAPI
) : ITCGRepository {

    override suspend fun getCards(name: String?) =
        api.getCards(name)
            .map {
                Card(
                    id = it.id,
                    name = it.name,
                    localId = it.localId,
                    image = it.image,
                )
            }

    override suspend fun getCardById(id: String) =
        api.getCardById(id)
            .run {
                CardDetails(
                    id = this.id,
                    image = this.image,
                    name = this.name,
                    rarity = this.rarity,
                    description = this.description,
                )
            }
}
