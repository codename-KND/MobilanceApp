package com.example.mobiuser.presentation.CompleteTrip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiuser.domain.model.request_id
import com.example.mobiuser.domain.usecase.completTrip.completeTripUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CompleteTripViewModel @Inject constructor(
    private val completeTrip: completeTripUC,

): ViewModel() {

    fun completeTrip(){
        viewModelScope.launch {
            completeTrip.completeTrip()
        }
    }

}
