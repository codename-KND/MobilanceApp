package com.example.mobiuser.presentation.AcceptTrip

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mobiuser.Goto
import com.example.mobiuser.presentation.GetRequestsScreen.GetTripsViewModel
import com.google.android.gms.location.LocationServices



@Composable

fun ConfirmedTrip(
    tripId: Int, navController: NavController,
    acceptTripViewModel: AcceptTripViewModel = hiltViewModel(),
    getTripsViewModel: GetTripsViewModel = hiltViewModel(),

){
    val details by getTripsViewModel.singleTrip.observeAsState()

    LaunchedEffect(Unit) {

      acceptTripViewModel.acceptTrip(tripId)
        getTripsViewModel.fetchThisTrip(tripId)
    }


    var hospitalLatitude =details?.hospitalLatitude
    var hospitalLongitude=details?.hospitalLongitude
    var pickLatitude=details?.pickLatitude
    var pickLongitude=details?.pickLongitude
    var userLatitude = remember { mutableStateOf(0.0) }
    var userLongitude = remember { mutableStateOf(0.0) }
    var isButtonVisible = remember { mutableStateOf(true) }
    val context = LocalContext.current
    var mapIntentUri by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ShowGlobalMapIntentUri(context,mapIntentUri,navController)
        if (isButtonVisible.value) {
            Button(
                onClick = {
                    // Hide the button and get user's location

                    isButtonVisible.value = false
                    getLocation({ latitude, longitude ->
                        userLatitude.value = latitude
                        userLongitude.value = longitude
                    }, context) {
                        // Launch Google Maps with start and end points
                         mapIntentUri = "https://www.google.com/maps/dir/?api=1" +
                                 "&origin=${userLatitude.value},${userLongitude.value}" +
                                 "&destination=$hospitalLatitude,$hospitalLongitude" +
                                 "&waypoints=$pickLatitude,$pickLongitude"
                        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapIntentUri))
                        Log.i("Google","$mapIntentUri")
                        Log.i("Google","$userLatitude")
                        Log.i("Google","${userLatitude.value}")
                        val maptwo =mapIntentUri
                        mapIntent.setPackage("com.google.android.apps.maps")
                    }
                },
                enabled = isButtonVisible.value
            ) {
                Text("Start Trip")
            }
        }

    }
}


private fun getLocation(
    onLocationReceived: (latitude: Double, longitude: Double) -> Unit,
    context: Context,
    onCompletion: () -> Unit
) {
    val LOCATION_PERMISSION_REQUEST_CODE = 123
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    // Check if the app has the necessary permissions
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                onLocationReceived(location.latitude, location.longitude)
            } else {
                Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show()
            }
            onCompletion()
        }.addOnFailureListener { exception ->
            Toast.makeText(context, "Failed to get location", Toast.LENGTH_SHORT).show()
            Log.e(TAG, "Error getting location", exception)
            onCompletion()
        }
    } else {

        ActivityCompat.requestPermissions(context as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
    }
}

