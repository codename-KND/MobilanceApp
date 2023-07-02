package com.example.mobiuser.domain.usecase.requestAmbulanceUC

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobiuser.domain.model.Authorization
import com.example.mobiuser.domain.model.RequestData
import com.example.mobiuser.domain.model.ServerResponse
import com.example.mobiuser.domain.repository.DjangoRepository
import com.example.mobiuser.domain.tokens.TokenHandler
import javax.inject.Inject
//
//class RequestAmbulanceUC @Inject constructor(
//    private val repository: DjangoRepository) {
//
//
//    sealed class Result {
//        data class Success(val serverResponse: ServerResponse) : Result()
//        data class Error(val message: String) : Result()
//    }
//
//    private val _requestResult = MutableLiveData<Result>()
//    val requestResult: LiveData<Result> = _requestResult
//
//    suspend fun requestAmbulance(requestData: RequestData) {
//        try {
//            val serverResponse = repository.requestAmbulance(requestData)
//            _requestResult.postValue(Result.Success(serverResponse))
//        } catch (e: Exception) {
//            _requestResult.postValue(Result.Error("Failed to request ambulance: ${e.message}"))
//        }
//    }
//    init {
//        requestResult.observeForever { result ->
//            when (result) {
//                is RequestAmbulanceUC.Result.Success -> {
//                    val serverResponse = result.serverResponse
//                    // Handle success case
//                }
//                is RequestAmbulanceUC.Result.Error -> {
//                    val errorMessage = result.message
//                    // Handle error case
//                }
//            }
//        }
//    }}

class RequestAmbulanceUC @Inject constructor(
    private val repository: DjangoRepository,
    private val tokenHandler: TokenHandler
) {
    sealed class Result {
        data class Success(val serverResponse: ServerResponse) : Result()
        data class Error(val message: String) : Result()
    }

    private val _requestResult = MutableLiveData<Result>()
    val requestResult: LiveData<Result> = _requestResult

    suspend fun requestAmbulance(
        contact: String,
        patient: String,
        pickLatitude: Double,
        pickLongitude: Double,
        hospitalLatitude: Double,
        hospitalLongitude: Double
    ) {
        val requestData = RequestData(
            patient = patient,
            pickLatitude = pickLatitude,
            pickLongitude = pickLongitude,
            contact = contact,
            hospitalLatitude = hospitalLatitude,
            hospitalLongitude = hospitalLongitude
        )
        val token = tokenHandler.getToken()
        val headers = Authorization(token)

        try {
            val serverResponse = repository.requestAmbulance(headers,requestData)
            Log.i("usecaseData", "$headers")
            _requestResult.postValue(Result.Success(serverResponse))
        } catch (e: Exception) {
            _requestResult.postValue(Result.Error("Failed to request ambulance: ${e.message}"))
        }
    }
}


