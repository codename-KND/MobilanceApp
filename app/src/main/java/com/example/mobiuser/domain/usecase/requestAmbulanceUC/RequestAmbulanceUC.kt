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
        val headers = "token "+tokenHandler.getToken()

        Log.i("UserLog Auth token", "$headers")
        Log.i("userLog data", "$requestData")
        try {
            val serverResponse = repository.requestAmbulance(headers,requestData)
            Log.i("usecaseData", "$headers")
            _requestResult.postValue(Result.Success(serverResponse))
        } catch (e: Exception) {
            _requestResult.postValue(Result.Error("Failed to request ambulance: ${e.message}"))
        }
    }
}


