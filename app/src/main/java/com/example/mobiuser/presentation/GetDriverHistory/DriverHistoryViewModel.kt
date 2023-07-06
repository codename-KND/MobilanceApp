package com.example.mobiuser.presentation.GetDriverHistory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiuser.domain.model.TripsResponseItem
import com.example.mobiuser.domain.usecase.GetDriverTrips.GetDriverTripsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DriverHistoryViewModel @Inject constructor(
    private val getDriverTripsUC: GetDriverTripsUC
): ViewModel() {

    private val _trips : MutableLiveData<List<TripsResponseItem>> = MutableLiveData()

    val trips: LiveData<List<TripsResponseItem>>
        get()=_trips
    init {
        fetchTrips()
    }
    fun fetchTrips() {
        viewModelScope.launch {

            getDriverTripsUC().collect{trips->
                _trips.value = trips
            }
            Log.i("GetTrips","func called")

        }
    }

}