package com.example.mobiuser.presentation.requestAmbulance

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobiuser.R
import com.example.mobiuser.domain.model.hospitals
import com.example.mobiuser.presentation.details.HospitalDropdown
import com.example.mobiuser.presentation.components.Signs
import com.example.mobiuser.presentation.components.Validator
import com.example.mobiuser.presentation.components.appRes
import com.example.mobiuser.presentation.details.GetLocationButton


@Composable
fun Request(
    navController: NavController,
    requestViewModel: RequestAmbulanceViewModel = hiltViewModel(),

){
        val imports = Signs()
        val head = appRes()
        val validation = Validator()
        val context = LocalContext.current
    var patient = remember {(mutableStateOf(""))}
    var contact = remember {(mutableStateOf(""))}
    var pickLatitude by remember { mutableStateOf(0.0) }
    var pickLongitude by remember { mutableStateOf(0.0) }

    var hospitalLatitude by remember { mutableStateOf(0.0) }
    var hospitalLongitude by remember { mutableStateOf(0.0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceAround,
    modifier = Modifier.fillMaxSize(1f)) {
        

      Column(horizontalAlignment = Alignment.CenterHorizontally){
          head.words(id = R.string.header)
          Spacer(modifier = Modifier.height(32.dp))
          imports.Textfield(detail = patient, fieldname = "Patient Name")
          GetLocationButton { newLatitude, newLongitude ->
              pickLatitude = newLatitude
              pickLongitude = newLongitude
          }
          imports.Textfield(detail = contact, fieldname = "Your Contact")
          HospitalDropdown(hospitals= hospitals) { latitude, longitude ->
              hospitalLatitude = latitude
              hospitalLongitude = longitude
          }
          Spacer(modifier = Modifier.height(32.dp))
          Column(
              verticalArrangement = Arrangement.SpaceBetween,
              horizontalAlignment = Alignment.CenterHorizontally
          ) {
              imports.Buttons(
                  onClick = {
                      val name = patient.value
                      ///val location = pickloc.value
                      val  contact = contact.value
                      val pickLatitude = pickLatitude
                      val pickLongitude = pickLongitude
                      val hospitalLatitude = hospitalLatitude
                      val hospitalLongitude = hospitalLongitude

                      Toast.makeText(context, "Location found Successfully.Latitude: $pickLatitude, Longitude: $pickLongitude", Toast.LENGTH_SHORT).show()

                      // Validate user inputs
                      if(validation.checkForm(context= context,contact,  name, hospitalLatitude,hospitalLongitude)){
                          requestViewModel.requestData(navController,contact,name,pickLatitude,pickLongitude, hospitalLatitude,hospitalLongitude)
                          }   //submit to database django

                      else{
                          Toast.makeText(context," Please try again", Toast.LENGTH_SHORT).show()
                      }
                            },
                  id = R.string.submit
              )
          }

      }
    }
}
// Create onclick that displays a message alert to show confirmation fees
// on confirmation then submit