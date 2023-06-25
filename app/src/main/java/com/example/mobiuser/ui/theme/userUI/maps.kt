package com.example.mobiuser.ui.theme.userUI

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
@Composable
fun mapScreen(navController: NavController) {
    AndroidView(
        factory = { context ->
            MapView(context).apply {

            }
        },
        update = { mapView ->
            mapView.getMapAsync { googleMap ->
                val sydney = LatLng(-34.0, 151.0)
                googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
            }

        }
    )
}