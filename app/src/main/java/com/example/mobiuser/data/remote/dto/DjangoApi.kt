package com.example.mobilanceuser.data.remote.dto

import com.example.mobiuser.domain.model.AvailableTripsItem
import com.example.mobiuser.domain.model.LoginResponse
import com.example.mobiuser.domain.model.Credentials
import com.example.mobiuser.domain.model.SignUpRequest
import com.example.mobiuser.domain.model.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface DjangoApi {
    @POST("authenticate")
    suspend fun authenticateUser(@Body credentials: Credentials): LoginResponse

    @POST("driverRegister")
    suspend fun signUp(@Body request: SignUpRequest): SignUpResponse

    @GET("available_requests")
    suspend fun getRequests(@Header("Authorization") Authorization: String):List<AvailableTripsItem>

    @GET("available_requests/{request_id}")
    suspend fun getThisRequests(@Header("Authorization") Authorization: String, @Path("request_id") requestId: Int,):AvailableTripsItem


}