package com.example.mobiuser.domain.usecase.loginUser

import android.util.Log
import com.example.mobiuser.data.repository.DjangoRepositoryImpl
import com.example.mobiuser.domain.model.LoginResponse
import com.example.mobiuser.domain.model.Credentials
import com.example.mobiuser.domain.repository.DjangoRepository
import com.example.mobiuser.domain.tokens.TokenHandler
import javax.inject.Inject


class loginUserUC @Inject constructor(
    private val repository: DjangoRepository,
    private val tokenHandler: TokenHandler
    ) {

    sealed class Result {
        data class Success(val loginResponse: LoginResponse) : Result()
        data class Error(val errorMessage: String) : Result()
    }

    suspend fun login(credentials: Credentials): Result {
        try {

            val loginResponse = repository.authenticateUser(credentials)

            Log.i("loginResponse", "Token: ${loginResponse.token}")
        /// store token to shared preference

            if ( loginResponse.token != null) {
                // Successful login response
                /// store token to shared preference
                tokenHandler.storeToken(loginResponse.token)
                Log.i("loginResponseSuccess", "Token: ${loginResponse.token}")
                return Result.Success(loginResponse)
            } else if (!loginResponse.errors.isNullOrEmpty()) {
                // Failed login response
                val errorMessage = loginResponse.errors.joinToString("\n")
                return Result.Error(errorMessage)
            } else {
                // Unexpected response format
                return Result.Error("Unexpected response format. Please try again.")
            }
            return Result.Success(loginResponse)
        } catch (e: Exception) {
            Log.e("loginUserUC", "Exception occurred: ${e.message}")
            return Result.Error("Login failed. Please try again.")
        }
    }
}