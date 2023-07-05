package com.example.mobiuser.presentation.detailsScreens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobiuser.domain.model.AvailableTripsItem
import com.example.mobiuser.presentation.GetRequestsScreen.GetTripsViewModel


@Composable
fun AcceptTrip(trip:Int,getTripsViewModel: GetTripsViewModel = hiltViewModel() ) {

    LaunchedEffect(Unit) {
       // getTripsViewModel.fetchSingleTrip(trip)
    }


    // Display the details of the clicked trip
    //Text(text = "Patient: ${trip.patient}")
    //Text(text = "Contact Detail: ${trip.contact}")

}