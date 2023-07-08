package com.example.mobiuser.presentation.GetRequestsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiuser.domain.model.AvailableTripsItem
import com.example.mobiuser.domain.usecase.GetRequests.GetRequestsUC
import com.example.mobiuser.domain.usecase.GetRequests.GetThisRequestUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetTripsViewModel @Inject constructor(
    private val  getTripsUC: GetRequestsUC,
    private val getThisRequestUC: GetThisRequestUC
) : ViewModel() {


private val _availableTrips: MutableLiveData<List<AvailableTripsItem>> = MutableLiveData()
    val availableTrips: LiveData<List<AvailableTripsItem>>
        get() = _availableTrips

private val _singleTrip: MutableLiveData<AvailableTripsItem> by lazy{ MutableLiveData<AvailableTripsItem>()}
    val singleTrip: LiveData<AvailableTripsItem>
        get()=_singleTrip
    init {
        fetchAvailableTrips()
    }

     fun fetchAvailableTrips() {
        viewModelScope.launch {
            getTripsUC().collect { trips ->
                _availableTrips.value = trips
            }
        }
    }
    fun fetchThisTrip(trip: Int) {
        viewModelScope.launch {
            try {
                val tripDetails = getThisRequestUC.getThisRequest(trip)
                _singleTrip.value = tripDetails?: tripDetails
            } catch (e: Exception) {
                // errors
            }
        }
    }
}
