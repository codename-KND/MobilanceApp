package com.example.mobilanceuser.data.remote.dto

import com.example.mobiuser.data.remote.dto.LoginResponse
import com.example.mobiuser.domain.model.Credentials
import com.example.mobiuser.domain.model.SignUpRequest
import com.example.mobiuser.domain.model.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DjangoApi {
    @POST("authenticate")
    suspend fun authenticateUser(@Body credentials: Credentials): LoginResponse

    @POST("register")
    suspend fun signUp(@Body request: SignUpRequest): SignUpResponse
}