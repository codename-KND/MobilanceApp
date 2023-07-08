package com.example.mobiuser.domain.usecase.completTrip

import com.example.mobiuser.domain.model.CompleteTripResponse
import com.example.mobiuser.domain.repository.DjangoRepository
import com.example.mobiuser.domain.tokens.TokenHandler
import javax.inject.Inject

class completeTripUC @Inject constructor(
    private val repository: DjangoRepository,

) {
    suspend fun completeTrip(pendingID:Int): CompleteTripResponse{

        val header ="Basic eUJFMURLYnZFaWM0Ykg5OEhibG44QTF3d3FPcU5yUkw6T1RCTzNGTHhiY3JoWWlyeA=="
        return repository.completeTrip(header,pendingID)
    }

}