package com.ricardo.tcg_dex.domain.use_case.get_card

import com.ricardo.tcg_dex.domain.model.CardDetails

interface IGetCardDetailsUseCase {
    suspend operator fun invoke(id: String): Result<CardDetails>
}
