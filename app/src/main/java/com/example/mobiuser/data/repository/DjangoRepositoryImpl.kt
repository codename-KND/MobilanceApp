package com.example.mobiuser.data.repository

import android.util.Log
import com.example.mobilanceuser.data.remote.dto.DjangoApi
import com.example.mobiuser.domain.model.AvailableTripsItem
import com.example.mobiuser.domain.model.LoginResponse
import com.example.mobiuser.domain.model.Credentials
import com.example.mobiuser.domain.model.SignUpRequest
import com.example.mobiuser.domain.model.SignUpResponse
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
}

