package com.example.mobiuser.domain.usecase.loginUser

import com.example.mobiuser.data.remote.dto.LoginResponse
import com.example.mobiuser.domain.model.Credentials
import com.example.mobiuser.domain.repository.DjangoRepository
import javax.inject.Inject

class loginUserUC @Inject constructor(private val repository: DjangoRepository) {

    sealed class Result {
        data class Success(val loginResponse: LoginResponse) : Result()
        data class Error(val errorMessage: String) : Result()
    }

    suspend fun login(credentials: Credentials): Result {
        try {
            val loginResponse = repository.authenticateUser(credentials)
            //token handling
            return Result.Success(loginResponse)
        } catch (e: Exception) {
            // Handle exceptions
            return Result.Error("Login failed. Please try again.")
        }
    }
}