package com.example.mobiuser.ui.theme.userUI

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
@Composable
fun mapScreens(navController: NavController) {

        val context = LocalContext.current
            var latitude =-1.2734000000000000
            var longitude =36.8061000000000000
        Button(
            onClick = {
                val mapIntentUri = "geo:$latitude,$longitude"
                val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapIntentUri))
                mapIntent.setPackage("com.google.android.apps.maps") // Specify Google Maps package
                if (mapIntent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(mapIntent)
                } else {
                    Toast.makeText(context, "Google Maps is not installed", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Launch Google Maps")
        }

}
