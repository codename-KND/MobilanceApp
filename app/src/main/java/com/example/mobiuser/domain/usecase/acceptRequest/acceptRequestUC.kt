package com.example.mobiuser.domain.usecase.acceptRequest

import com.example.mobiuser.domain.model.AcceptTripResponse
import com.example.mobiuser.domain.model.request_id
import com.example.mobiuser.domain.repository.DjangoRepository
import com.example.mobiuser.domain.tokens.TokenHandler
import javax.inject.Inject

class acceptRequestUC @Inject constructor(
    private val repository: DjangoRepository,
    private val tokenHandler: TokenHandler
) {

    suspend fun acceptTrip(requestID:request_id): AcceptTripResponse{

        val header = "token "+ tokenHandler.getToken()
        val requestId =tokenHandler.getRequestID().toInt()
        val send =request_id(requestId)
        val confirmation = repository.acceptRequest(header,send)
        val pendingID= confirmation.pending_id.toString()
        tokenHandler.storePendingID(pendingID)
        return confirmation
    }
}