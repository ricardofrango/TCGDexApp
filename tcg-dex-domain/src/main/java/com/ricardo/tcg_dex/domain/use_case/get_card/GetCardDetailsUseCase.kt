package com.ricardo.tcg_dex.domain.use_case.get_card

import com.ricardo.tcg_dex.domain.ITCGRepository
import com.ricardo.tcg_dex.domain.model.CardDetails
import javax.inject.Inject

class GetCardDetailsUseCase @Inject constructor(
    private val repository: ITCGRepository
) : IGetCardDetailsUseCase {
    override suspend fun invoke(id: String): Result<CardDetails> =
        runCatching { repository.getCardById(id) }
            .onFailure { Result.failure<CardDetails>(it) }
}
