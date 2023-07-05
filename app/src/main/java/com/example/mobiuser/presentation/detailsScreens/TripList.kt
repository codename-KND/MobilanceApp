package com.example.mobiuser.presentation.detailsScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobiuser.domain.model.AvailableTripsItem
import com.example.mobiuser.presentation.components.ViewModelFun


@Composable
fun TripList(
    trips: List<AvailableTripsItem>,
    onClick: () -> Unit
//    navController: NavController
) {


    LazyColumn {
        items(trips) { trip ->
            TripItem(trip, onClick )
        }
    }
}


@Composable
fun TripItem(
    trip: AvailableTripsItem,
    onClick: () -> Unit
//    navController: NavController
) {
    Column(
        modifier = Modifier
            .clickable {
                // Navigate to another screen when clicked
                //add onclick

                onClick()
//                navController.navigate("AcceptTrip/${trip.request_id}")
            }
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
        /**Add Card to hold items**/
        Column(modifier = Modifier.padding(10.dp)) {
            var help = ViewModelFun()
            val hospitalName = help.getHospitalName(trip.hospitalLatitude.toDouble(), trip.hospitalLongitude.toDouble())

            Text(text = "Patient: ${trip.patient}")
            Text(text = "Contact Detail: ${trip.contact}")
            Text(text = "Hospital: $hospitalName")

        }

    }
}