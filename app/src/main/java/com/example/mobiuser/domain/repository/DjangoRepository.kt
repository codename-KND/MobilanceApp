package com.example.mobiuser.domain.repository

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

interface DjangoRepository {

    suspend fun authenticateUser(credentials: Credentials): LoginResponse

    suspend fun signUpUser(signUpRequest: SignUpRequest): SignUpResponse

    suspend fun getRequests(header:String):List<AvailableTripsItem>

    suspend fun getThisRequest(header:String,path:Int): AvailableTripsItem

    suspend fun getTrips(header:String): List<TripsResponseItem>

    suspend fun acceptRequest(header:String,requestId:request_id): AcceptTripResponse

    suspend fun completeTrip(header:String,pendingId:pending_id): CompleteTripResponse
}