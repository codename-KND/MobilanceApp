package com.example.mobilanceuser.data.remote.dto

import com.example.mobiuser.domain.model.Authorization
import com.example.mobiuser.domain.model.LoginResponse
import com.example.mobiuser.domain.model.Credentials
import com.example.mobiuser.domain.model.RequestData
import com.example.mobiuser.domain.model.ServerResponse
import com.example.mobiuser.domain.model.SignUpRequest
import com.example.mobiuser.domain.model.SignUpResponse
import com.example.mobiuser.domain.model.TripsResponse
import com.example.mobiuser.domain.model.TripsResponseItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface DjangoApi {
    @POST("authenticate")
    suspend fun authenticateUser(@Body credentials: Credentials): LoginResponse

    @POST("register")
    suspend fun signUp(@Body request: SignUpRequest): SignUpResponse

    @POST("request")
    suspend fun requestAmbulance(@Header("Authorization") Authorization: String,
                                 @Body userRequest: RequestData): ServerResponse

    @GET("mytrips")
    suspend fun getTrips(@Header("Authorization") Authorization: String):List<TripsResponseItem>
}