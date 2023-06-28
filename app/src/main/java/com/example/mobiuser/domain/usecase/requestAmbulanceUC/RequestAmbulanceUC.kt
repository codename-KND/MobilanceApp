package com.example.mobiuser.domain.usecase.requestAmbulanceUC

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobiuser.domain.model.RequestData
import com.example.mobiuser.domain.model.ServerResponse
import com.example.mobiuser.domain.repository.DjangoRepository
import javax.inject.Inject

class RequestAmbulanceUC @Inject constructor(private val repository: DjangoRepository) {


    sealed class Result {
        data class Success(val serverResponse: ServerResponse) : Result()
        data class Error(val message: String) : Result()
    }

    private val _requestResult = MutableLiveData<Result>()
    val requestResult: LiveData<Result> = _requestResult

    suspend fun requestAmbulance(requestData: RequestData) {
        try {
            val serverResponse = repository.requestAmbulance(requestData)
            _requestResult.postValue(Result.Success(serverResponse))
        } catch (e: Exception) {
            _requestResult.postValue(Result.Error("Failed to request ambulance: ${e.message}"))
        }
    }





}