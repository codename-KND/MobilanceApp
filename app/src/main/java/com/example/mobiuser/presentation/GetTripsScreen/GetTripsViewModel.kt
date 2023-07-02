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

    private val _trips = MutableStateFlow<List<TripsResponseItem>>(emptyList())
    val trips: StateFlow<List<TripsResponseItem>> = _trips

    fun fetchTrips() {
        viewModelScope.launch {
            try {
                val tripsList = withContext(Dispatchers.IO) {
                    getTripsUC.getTrips()

                }
                Log.i("GetTrips","func called")
                _trips.value = tripsList
                Log.i("GetTripsViewModel", "Fetched trips: $tripsList")
            } catch (e: Exception) {
                // Handle error, show error message, etc.
            }
        }
    }
}