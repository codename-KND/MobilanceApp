package com.example.mobiuser.domain.usecase.GetTrips

import android.util.Log
import com.example.mobiuser.domain.model.TripsResponseItem
import com.example.mobiuser.domain.repository.DjangoRepository
import com.example.mobiuser.domain.tokens.TokenHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTripsUC @Inject constructor(
    private val repository: DjangoRepository,
    private val tokenHandler: TokenHandler
){
   operator fun invoke(): Flow<List<TripsResponseItem>> =flow{
        val header = "token "+tokenHandler.getToken()
        val tripsResponse = repository.getTrips(header)
        Log.i("trips", "$tripsResponse")
        emit(tripsResponse)
    }
}