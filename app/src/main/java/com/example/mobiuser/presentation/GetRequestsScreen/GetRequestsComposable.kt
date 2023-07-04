package com.example.mobiuser.presentation.GetRequestsScreen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobiuser.domain.model.AvailableTripsItem
import com.example.mobiuser.presentation.detailsScreens.TripList

@Composable
fun getRequests(navController: NavController,
    getTripsViewModel: GetTripsViewModel = hiltViewModel()) {
    val availableTrips: List<AvailableTripsItem> by getTripsViewModel.availableTrips.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        getTripsViewModel.fetchAvailableTrips()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Available Requests",color = Color.Black) },
                backgroundColor = Color.White,
                elevation = 12.dp
            )},
        content = {
            TripList(availableTrips,navController)
        }
    )
}
