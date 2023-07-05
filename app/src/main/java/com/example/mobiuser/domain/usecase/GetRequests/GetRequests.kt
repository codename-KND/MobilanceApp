package com.example.mobiuser.domain.usecase.GetRequests

import android.util.Log
import com.example.mobiuser.domain.model.AvailableTripsItem
import com.example.mobiuser.domain.repository.DjangoRepository
import com.example.mobiuser.domain.tokens.TokenHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRequestsUC @Inject constructor(
    private val repository: DjangoRepository,
    private val tokenHandler: TokenHandler
){
    operator fun invoke(): Flow<List<AvailableTripsItem>> = flow{

            val header = "token "+tokenHandler.getToken()
            val availableTrips = repository.getRequests(header)
            Log.i("trips", "$availableTrips")
            emit(availableTrips)

    }

}
