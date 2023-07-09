package com.example.mobiuser.domain.usecase.GetRequests

import android.util.Log
import com.example.mobiuser.domain.model.AvailableTripsItem
import com.example.mobiuser.domain.repository.DjangoRepository
import com.example.mobiuser.domain.tokens.TokenHandler
import javax.inject.Inject

class GetThisRequestUC @Inject constructor(
    private val repository: DjangoRepository,
    private val tokenHandler: TokenHandler
) {

    suspend fun getThisRequest(requestId:Int):AvailableTripsItem{
        val header = "token "+tokenHandler.getToken()
        val path = requestId
        val tripDetails = repository.getThisRequest(header,path)
        val storeID =tripDetails.request_id.toString()
        Log.i("storeID","$storeID")
        tokenHandler.storeRequestID(storeID)
        return tripDetails
    }
}