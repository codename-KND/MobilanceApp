package com.example.mobiuser.domain.repository

import com.example.mobiuser.domain.model.Authorization
import com.example.mobiuser.domain.model.LoginResponse
import com.example.mobiuser.domain.model.Credentials
import com.example.mobiuser.domain.model.RequestData
import com.example.mobiuser.domain.model.ServerResponse
import com.example.mobiuser.domain.model.SignUpRequest
import com.example.mobiuser.domain.model.SignUpResponse

interface DjangoRepository {

    suspend fun authenticateUser(credentials: Credentials): LoginResponse

    suspend fun signUpUser(signUpRequest: SignUpRequest): SignUpResponse

    suspend fun requestAmbulance(headers: Authorization, requestData: RequestData):ServerResponse
}