package com.example.mobiuser.presentation.AcceptTrip

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobiuser.domain.model.AvailableTripsItem


@Composable

fun ConfirmedTrip(
    availableTripsItem: Int,
    navController: NavController,
    acceptTripViewModel: AcceptTripViewModel = hiltViewModel()

){
//    LaunchedEffect(Unit) {
//        Log.i("availatbletrip","$availableTripsItem")
//      acceptTripViewModel.acceptTrip(availableTripsItem)
//    }
    
    Text(text = "$availableTripsItem")
}