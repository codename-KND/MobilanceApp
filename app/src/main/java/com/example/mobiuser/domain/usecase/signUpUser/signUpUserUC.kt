package com.example.mobiuser.domain.usecase.signUpUser

import android.util.Log
import com.example.mobiuser.domain.model.SignUpRequest
import com.example.mobiuser.domain.model.SignUpResponse
import com.example.mobiuser.domain.repository.DjangoRepository
import javax.inject.Inject

class signUpUserUC @Inject constructor(private val repository: DjangoRepository) {

    sealed class Result {
        data class Success(val signUpResponse: SignUpResponse) : Result()
        data class Error(val errorMessage: String) : Result()
    }

    suspend fun signUp(signUpRequest: SignUpRequest): Result {
        Log.i("signupUC", "signUp called")
        Log.i("signup", "${signUpRequest}")
        try {
            val signUpResponse = repository.signUpUser(signUpRequest)
            Log.i("Response", "Token: ${signUpResponse.token}")
            return Result.Success(signUpResponse)
        } catch (e: Exception) {
            return Result.Error("Sign-up failed. Please try again.")
        }
    }

}