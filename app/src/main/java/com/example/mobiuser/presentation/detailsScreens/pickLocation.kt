package com.example.mobiuser.presentation.detailsScreens

import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.location.LocationServices

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
