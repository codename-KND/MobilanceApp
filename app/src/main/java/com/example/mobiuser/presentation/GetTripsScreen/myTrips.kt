package com.example.mobiuser.presentation.GetTripsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobiuser.domain.model.TripsResponseItem
import com.example.mobiuser.domain.model.hospitals
import com.example.mobiuser.presentation.components.ViewModelFun


@Composable
fun MyApp(getTripsViewModel: GetTripsViewModel = hiltViewModel()) {

    val trips: List<TripsResponseItem> by getTripsViewModel.trips.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        getTripsViewModel.fetchTrips()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Your Trip History",color = Color.Black) },
                backgroundColor = Color(0x80FF6BA368),
                elevation = 12.dp
            )
        },
            content = {
                TripList(trips) }
            )
}

@Composable
fun TripList(trips: List<TripsResponseItem>) {
    LazyColumn {
        items(trips) { trip ->
            TripItem(trip)
        }
    }
}


@Composable
fun TripItem(trip: TripsResponseItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                width = 1.dp,
                color = Color.Red,
                shape = RoundedCornerShape(20)
            )
            .background(
                color = Color.Red.copy(alpha = 0.2f),
                shape = RoundedCornerShape(20)
            )
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            val hospitalName = getHospitalName(trip.hospitalLatitude.toDouble(), trip.hospitalLongitude.toDouble())


            Text(text = "Patient Name: ${trip.patientName}")
            Text(text = "Driver Name: ${trip.driver}")
            Text(text = "Hospital: $hospitalName")
        }

    }
}

fun getHospitalName(latitude: Double, longitude: Double): String? {
    val hospital = hospitals.find { it.latitude == latitude && it.longitude == longitude }

    return hospital?.name ?: "Hospital"
}