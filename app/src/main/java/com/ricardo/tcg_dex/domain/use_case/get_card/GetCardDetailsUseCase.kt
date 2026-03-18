package com.ricardo.tcg_dex.domain.use_case.get_card

import com.ricardo.tcg_dex.domain.model.CardDetails
import com.ricardo.tcg_dex.networking.ITCGRepository
import javax.inject.Inject

class GetCardDetailsUseCase @Inject constructor(
    private val repository: ITCGRepository
) : IGetCardDetailsUseCase {
    override suspend fun invoke(id: String): Result<CardDetails> = runCatching {
        val result = repository.getCardById(id)

        CardDetails(
            id = result.id,
            image = result.image,
            name = result.name,
        )
    }.onFailure { Result.failure<CardDetails>(it) }
}
