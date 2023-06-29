package com.example.mobiuser.presentation.requestAmbulance

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import com.example.mobiuser.R
import com.example.mobiuser.presentation.components.Signs
import com.example.mobiuser.presentation.components.Validator
import com.example.mobiuser.presentation.components.appRes
import com.example.mobiuser.presentation.loginScreen.LoginViewModel

@Composable
fun Request(
    navController: NavController,
    requestViewModel: RequestAmbulanceViewModel = hiltViewModel()
){
        val imports = Signs()
        val head = appRes()
        val validation = Validator()
        val context = LocalContext.current
    var patient = remember {(mutableStateOf(""))}
    var pickloc = remember {(mutableStateOf(""))}
    var contact = remember {(mutableStateOf(""))}
    var droploc = remember {(mutableStateOf(""))}
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceAround,
    modifier = Modifier.fillMaxSize(1f)) {
        

      Column(horizontalAlignment = Alignment.CenterHorizontally){
          head.words(id = R.string.header)
          Spacer(modifier = Modifier.height(32.dp))
          imports.Textfield(detail = patient, fieldname = "Patient Name")
          imports.Textfield(detail = pickloc, fieldname = "Location")//placeholder
          imports.Textfield(detail = contact, fieldname = "Your Contact")
          imports.Textfield(detail = droploc, fieldname = "Your Preferred Hospital")//placeholder

          Spacer(modifier = Modifier.height(32.dp))
          Column(
              verticalArrangement = Arrangement.SpaceBetween,
              horizontalAlignment = Alignment.CenterHorizontally
          ) {
              imports.Buttons(
                  onClick = {
                      val name = patient.value
                      val location = pickloc.value
                      val  contact = contact.value
                      val hospital = droploc.value


                      // Validate user inputs
                      if(validation.checkForm(context= context,contact,  name, location, hospital)){
                          requestViewModel.requestData(navController,contact,  name, location, hospital)}   //submit to database django

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