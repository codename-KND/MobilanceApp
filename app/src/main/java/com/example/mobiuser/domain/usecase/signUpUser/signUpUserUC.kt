package com.example.mobiuser.domain.usecase.signUpUser

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
        try {
            val signUpResponse = repository.signUpUser(signUpRequest)
            return Result.Success(signUpResponse)
        } catch (e: Exception) {
            return Result.Error("Sign-up failed. Please try again.")
        }
    }

}