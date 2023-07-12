package com.example.mobilanceuser.data.remote.dto

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
    suspend fun getThisRequests(@Header("Authorization") Authorization: String,
                                @Path("request_id") requestId: Int,):AvailableTripsItem

    @GET("drivertrips")
    suspend fun getTrips(@Header("Authorization") Authorization: String):List<TripsResponseItem>

    @POST("accept_request")
    suspend fun acceptRequest(@Header("Authorization") Authorization: String,
                              @Body request_id: request_id):AcceptTripResponse

    @POST("complete_trip")
    suspend fun completeTrip(@Header("Authorization") Authorization: String,
                                @Body pending_id: pending_id): CompleteTripResponse

    /////MAP API


}