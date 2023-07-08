package com.example.mobiuser.presentation.AcceptTrip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiuser.domain.model.AcceptTripResponse
import com.example.mobiuser.domain.model.request_id
import com.example.mobiuser.domain.usecase.acceptRequest.acceptRequestUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AcceptTripViewModel @Inject constructor(
  private val acceptRequestUC: acceptRequestUC,
): ViewModel(){

    private val _acceptTripLiveData: MutableLiveData<AcceptTripResponse> = MutableLiveData()
    val acceptTrip: LiveData<AcceptTripResponse> get() = _acceptTripLiveData

    fun acceptTrip(pendingId: Int) {
        viewModelScope.launch {
            val requestId =request_id(pendingId)
            val response = acceptRequestUC.acceptTrip(requestId)
            _acceptTripLiveData.value = response
        }
    }
}

