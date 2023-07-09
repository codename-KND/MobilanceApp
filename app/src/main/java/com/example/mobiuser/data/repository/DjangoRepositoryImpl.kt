package com.example.mobiuser.data.repository

import android.util.Log
import com.example.mobilanceuser.data.remote.dto.DjangoApi
import com.example.mobiuser.domain.model.AcceptTripResponse
import com.example.mobiuser.domain.model.AvailableTripsItem
import com.example.mobiuser.domain.model.CompleteTripResponse
import com.example.mobiuser.domain.model.LoginResponse
import com.example.mobiuser.domain.model.Credentials
import com.example.mobiuser.domain.model.SignUpRequest
import com.example.mobiuser.domain.model.SignUpResponse
import com.example.mobiuser.domain.model.TripsResponseItem
import com.example.mobiuser.domain.model.pending_id
import com.example.mobiuser.domain.model.request_id
import com.example.mobiuser.domain.repository.DjangoRepository
import javax.inject.Inject


class DjangoRepositoryImpl@Inject constructor(
    private val api :DjangoApi,
   // private val tokenHandler: TokenHandler
    ):DjangoRepository {



    override suspend fun authenticateUser(credentials: Credentials): LoginResponse {

        Log.i("DjangoRepositoryImpl", "authenticateUser called here")
        return api.authenticateUser(credentials)
    }

    override suspend fun signUpUser(signUpRequest: SignUpRequest): SignUpResponse {
        Log.i("signup", "signUpUser called in impl")

        return api.signUp(signUpRequest)
    }


    override suspend fun getRequests(header: String): List<AvailableTripsItem> {
        return api.getRequests(header)
    }

    override suspend fun getThisRequest(header: String, path: Int): AvailableTripsItem {
        return api.getThisRequests(header, path)
    }
    override suspend fun getTrips(header: String): List<TripsResponseItem> {
        return api.getTrips(header)
    }

    override suspend fun acceptRequest(header: String, requestId: request_id): AcceptTripResponse {
        return api.acceptRequest(header, requestId)
    }

    override suspend fun completeTrip(header: String, pendingId: pending_id): CompleteTripResponse {
        return api.completeTrip(header, pendingId)
    }
    ///Add map API endpoint
}

