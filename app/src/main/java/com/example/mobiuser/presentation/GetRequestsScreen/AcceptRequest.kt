package com.example.mobiuser.presentation.GetRequestsScreen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobiuser.domain.model.AvailableTripsItem
import com.example.mobiuser.presentation.GetRequestsScreen.GetTripsViewModel


@Composable
fun AcceptTrip(trip:Int,getTripsViewModel: GetTripsViewModel = hiltViewModel() ) {

    val details by getTripsViewModel.singleTrip.observeAsState()

    LaunchedEffect(Unit) {
       getTripsViewModel.fetchThisTrip(trip)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Available Requests",color = Color.Black) },
                backgroundColor = Color.White,
                elevation = 12.dp
            )
        },
        content = {
            Text(text = "Trip ID: ${details?.request_id}")
              Text(text = "Patient: ${details?.patient}")})


}