package com.example.mobiuser.presentation.GetTripsScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiuser.domain.model.TripsResponseItem
import com.example.mobiuser.domain.usecase.GetTrips.GetTripsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GetTripsViewModel @Inject constructor(
    private val  getTripsUC: GetTripsUC
) : ViewModel() {

    private val _trips :MutableLiveData<List<TripsResponseItem>> = MutableLiveData()

    val trips: LiveData<List<TripsResponseItem>>
        get()=_trips
    init {
        fetchTrips()
    }
    fun fetchTrips() {
        viewModelScope.launch {

                    getTripsUC().collect{trips->
                        _trips.value = trips
                }
                Log.i("GetTrips","func called")

        }
    }
}