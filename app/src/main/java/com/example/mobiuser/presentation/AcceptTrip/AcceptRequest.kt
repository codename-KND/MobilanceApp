package com.example.mobiuser.presentation.AcceptTrip

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import com.example.mobiuser.R
import com.example.mobiuser.domain.model.request_id
import com.example.mobiuser.presentation.GetRequestsScreen.GetTripsViewModel
import com.example.mobiuser.presentation.components.Signs


@Composable
fun AcceptTrip(
    trip: Int,
    navController: NavController,
    getTripsViewModel: GetTripsViewModel = hiltViewModel()
) {
    val details by getTripsViewModel.singleTrip.observeAsState()
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        getTripsViewModel.fetchThisTrip(trip)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Request Details", color = Color.Black) },
                backgroundColor = Color.White,
                elevation = 12.dp
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .background(color = Color.Black)
                    .padding(7.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                //verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Here is the Trip Summary",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(28.dp))

                Card(modifier = Modifier.fillMaxWidth(1f)) {
                    Column() {
                        Text(text = "Patient name: ${details?.patient}")
                        Spacer(modifier = Modifier.height(7.dp))
                        Text(text = "Trip ID: ${details?.request_id}")
                        Spacer(modifier = Modifier.height(7.dp))
                        Text(text = "Contact Detail: ${details?.contact}")
                        Spacer(modifier = Modifier.height(7.dp))
                    }
                }
                Spacer(modifier = Modifier.height(21.dp))
                var help = Signs()
                help.Buttons(onClick = { showDialog.value = true }, id = R.string.getTrips)
                Spacer(modifier = Modifier.height(21.dp))
                /**Comment :amend pass trip id to confirmation popup**/
                    //ammendment pass trip id
                if (showDialog.value) {
                    ConfirmationPopup(onConfirm = {
                        navController.navigate(Goto.ConfirmedTrip.route) {
                            launchSingleTop = true
                            popUpTo(Goto.AcceptTrip.route) { saveState = true }
                            // Pass the confirmedTrip as an argument to the destination
//                            this.arguments = bundleOf("confirmedTrip" to confirmedTrip )
                            Toast.makeText(context," Confirmed. Setting up trip",Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        }
    )
}





