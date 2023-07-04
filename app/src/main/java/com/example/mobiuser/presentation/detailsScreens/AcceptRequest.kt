package com.example.mobiuser.presentation.detailsScreens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.mobiuser.domain.model.AvailableTripsItem


@Composable
fun AcceptTrip(trip: AvailableTripsItem) {
    // Display the details of the clicked trip
    Text(text = "Patient: ${trip.patient}")
    Text(text = "Contact Detail: ${trip.contact}")

}