package com.example.mobiuser.domain.usecase.completTrip

import com.example.mobiuser.domain.model.CompleteTripResponse
import com.example.mobiuser.domain.model.pending_id
import com.example.mobiuser.domain.model.request_id
import com.example.mobiuser.domain.repository.DjangoRepository
import com.example.mobiuser.domain.tokens.TokenHandler
import javax.inject.Inject

class completeTripUC @Inject constructor(
    private val repository: DjangoRepository,
    private val tokenHandler: TokenHandler

) {
    suspend fun completeTrip(): CompleteTripResponse{

        val header ="Basic eUJFMURLYnZFaWM0Ykg5OEhibG44QTF3d3FPcU5yUkw6T1RCTzNGTHhiY3JoWWlyeA=="
        val pending = tokenHandler.getPendingID().toInt()
        val send= pending_id(pending)
        return repository.completeTrip(header,send)
    }

}