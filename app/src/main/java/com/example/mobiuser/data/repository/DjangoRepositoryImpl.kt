package com.example.mobiuser.data.repository

import com.example.mobilanceuser.data.remote.dto.DjangoApi
import com.example.mobiuser.domain.model.LoginResponse
import com.example.mobiuser.domain.model.Credentials
import com.example.mobiuser.domain.model.RequestData
import com.example.mobiuser.domain.model.ServerResponse
import com.example.mobiuser.domain.model.SignUpRequest
import com.example.mobiuser.domain.model.SignUpResponse
import com.example.mobiuser.domain.repository.DjangoRepository
import javax.inject.Inject


class DjangoRepositoryImpl@Inject constructor(private val api :DjangoApi):DjangoRepository {

    override suspend fun authenticateUser(credentials: Credentials): LoginResponse {
        return api.authenticateUser(credentials)
    }

    override suspend fun signUpUser(signUpRequest: SignUpRequest): SignUpResponse {
        return api.signUp(signUpRequest)
    }

    override suspend fun requestAmbulance(requestData: RequestData): ServerResponse {
        return api.requestAmbulance(requestData)
    }


}

