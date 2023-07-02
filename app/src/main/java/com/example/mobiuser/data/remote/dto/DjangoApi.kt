package com.example.mobilanceuser.data.remote.dto

import com.example.mobiuser.domain.model.Authorization
import com.example.mobiuser.domain.model.LoginResponse
import com.example.mobiuser.domain.model.Credentials
import com.example.mobiuser.domain.model.RequestData
import com.example.mobiuser.domain.model.ServerResponse
import com.example.mobiuser.domain.model.SignUpRequest
import com.example.mobiuser.domain.model.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface DjangoApi {
    @POST("authenticate")
    suspend fun authenticateUser(@Body credentials: Credentials): LoginResponse

    @POST("register")
    suspend fun signUp(@Body request: SignUpRequest): SignUpResponse

    @POST("request")
    suspend fun requestAmbulance(@Header("Authorization") token: Authorization, @Body userRequest: RequestData): ServerResponse
}