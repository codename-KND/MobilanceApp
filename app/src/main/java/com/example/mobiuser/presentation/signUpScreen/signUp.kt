package com.example.mobiuser.presentation.signUpScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import com.example.mobiuser.R
import com.example.mobiuser.presentation.components.Signs
import com.example.mobiuser.presentation.components.Validator

@Composable
fun SignUp(
    navController: NavController,
    signUpViewModel: SignUpViewModel = hiltViewModel()) {
    val resor = Signs()
    val email = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }
    val confpass = remember { mutableStateOf("") }
    val validation= Validator()
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = Color.Black)
    ) {
        Column() {
            Text(
                text = " Sign Up",
                color = Color.Red,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = " Help us Get You Ready",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
        Column(verticalArrangement = Arrangement.SpaceAround) {
            resor.EmailField(email = email)
            resor.Textfield(detail = username, fieldname = "Username")
            resor.Pass(password = password, showPassword = showPassword, text = "")
            resor.Pass(password = confpass, showPassword = showPassword, text = "Confirm")


        }
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            resor.Buttons(
                onClick = {
                    val email = email.value
                    val username = username.value
                    val password = password.value
                    val confpass = confpass.value
//submission
    //set onclick listener to submit to db if conditions met else reenter invalid details


                    if(validation.checkSignUp(context, email, username, password, confpass)){
                        //send data to database
                        signUpViewModel.signUp(email,username,password,navController,context)}
                          },
                id = R.string.submit
            )
        }



        Spacer(modifier = Modifier.height(48.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.gotAcc),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 15.sp,
            )
            Spacer(modifier = Modifier.width(16.dp))
            resor.Buttons(
                onClick = {

                        navController.navigate(Goto.Login.route){popUpTo(Goto.Login.route)}
                          }, id = R.string.login
            )
        }

    }


}

