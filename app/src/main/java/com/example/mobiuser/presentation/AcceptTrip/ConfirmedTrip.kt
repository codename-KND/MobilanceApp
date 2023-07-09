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
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices



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
    //get availableItem list
    Text(text = "$availableTripsItem")



    var hospitalLatitude =-1.2734000000000000
    var hospitalLongitude=36.8061000000000000
    var pickLatitude=-1.2618564000000000
    var pickLongitude=36.8102071000000000

    var userLatitude = remember { mutableStateOf(0.0) }
    var userLongitude = remember { mutableStateOf(0.0) }
    var isButtonVisible = remember { mutableStateOf(true) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                        val mapIntentUri = "https://www.google.com/maps/dir/?api=1" +
                                "&origin=$userLatitude,$userLongitude" +
                                "&destination=$hospitalLatitude,$hospitalLongitude" +
                                "&waypoints=$pickLatitude,$pickLongitude"
                        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapIntentUri))
                        mapIntent.setPackage("com.google.android.apps.maps") // Specify Google Maps package
                        if (mapIntent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(mapIntent)
                        } else {
                            Toast.makeText(context, "Google Maps is not installed", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                enabled = isButtonVisible.value
            ) {
                Text("Pick Location and Launch Google Maps")
            }
        }
    }

}

@Composable
fun GetLocationButton(onLocationReceived: (latitude: Double, longitude: Double) -> Unit) {
    var isButtonVisible by remember { mutableStateOf(true) }
    val showProgress = remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (isButtonVisible) {
        Button(
            onClick = {
                isButtonVisible = false
                showProgress.value = true
                getLocation(onLocationReceived, context) {
                    showProgress.value = false

                }
            },
            enabled = !showProgress.value
        ) {
            Text("Use my location")
        }
    }
    if (showProgress.value) {
        CircularProgressIndicator()
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
        // Request the necessary permissions
        ActivityCompat.requestPermissions(context as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
    }
}
