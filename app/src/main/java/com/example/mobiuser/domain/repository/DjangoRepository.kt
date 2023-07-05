package com.example.mobiuser.domain.repository

import com.example.mobiuser.domain.model.AvailableTripsItem
import com.example.mobiuser.domain.model.LoginResponse
import com.example.mobiuser.domain.model.Credentials
import com.example.mobiuser.domain.model.SignUpRequest
import com.example.mobiuser.domain.model.SignUpResponse

interface DjangoRepository {

    suspend fun authenticateUser(credentials: Credentials): LoginResponse

    suspend fun signUpUser(signUpRequest: SignUpRequest): SignUpResponse

    suspend fun getRequests(header:String):List<AvailableTripsItem>

    suspend fun getThisRequest(header:String,path:Int): AvailableTripsItem
}