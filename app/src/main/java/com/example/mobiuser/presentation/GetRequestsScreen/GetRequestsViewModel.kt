package com.example.mobiuser.presentation.GetRequestsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiuser.domain.model.AvailableTripsItem
import com.example.mobiuser.domain.usecase.GetRequests.GetRequestsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetTripsViewModel @Inject constructor(
    private val  getTripsUC: GetRequestsUC
) : ViewModel() {

//    private val _requests = MutableStateFlow<List<AvailableTripsItem>>(emptyList())
//    val requests: StateFlow<List<AvailableTripsItem>> = _requests
//    fun fetchTrips() {
//        viewModelScope.launch {
//            try {
//                val requestList = withContext(Dispatchers.IO) {
//                    getTripsUC.getTrips()
//
//                }
//                Log.i("GetTrips","func called")
//                _requests.value = requestList
//                Log.i("GetTripsViewModel", "Fetched trips: $_requests")
//            } catch (e: Exception) {
//                // Handle error, show error message, etc.
//            }
//        }
//    }
private val _availableTrips: MutableLiveData<List<AvailableTripsItem>> = MutableLiveData()
    val availableTrips: LiveData<List<AvailableTripsItem>>
        get() = _availableTrips

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
}
