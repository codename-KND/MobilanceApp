package com.example.mobiuser.ui.theme.userUI
//
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.mobiuser.domain.model.TripsResponseItem
//import com.example.mobiuser.domain.model.hospitals
//
//
//
//@Composable
//fun MyApp(getTripsViewModel: GetTripsViewModel = hiltViewModel()) {
//
//    //val trips: List<TripsResponseItem> by getTripsViewModel.trips.collectAsState()
//
//    LazyColumn {
//        items(trips) { trip ->
//            val hospitalName = getHospitalName(trip.hospitalLatitude.toDouble(), trip.hospitalLongitude.toDouble())
//            Text(text = "Driver: ${trip.driver}")
//            Text(text = "Hospital Latitude: ${trip.hospitalLatitude}")
//            Text(text = "Hospital Longitude: ${trip.hospitalLongitude}")
//            Text(text = "Hospital Name: $hospitalName")
//            Text(text = "Patient Name: ${trip.patientName}")
//            Text(text = "Request ID: ${trip.requestId}")
//        }
//    }
//}
//
//fun getHospitalName(latitude: Double, longitude: Double): String? {
//    val hospital = hospitals.find { it.latitude == latitude && it.longitude == longitude }
//    return hospital?.name
//}