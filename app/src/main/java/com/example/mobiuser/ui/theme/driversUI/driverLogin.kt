package com.example.mobiuser.ui.theme.driversUI

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import com.example.mobiuser.presentation.components.Signs
import com.example.mobiuser.presentation.components.Validator
import com.example.mobiuser.R


@Composable
fun driverLogin(navController: NavController) {

    val logo = painterResource(id = R.drawable.laun)
    val user = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val validation = Validator()


    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(color = Color.Black)
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //page content
        Column {

            Image(
                painter = logo, contentDescription = null,
                modifier = Modifier
                    .height(250.dp)
                    .clip(shape = RoundedCornerShape(7.dp))
                    .width(300.dp),
                contentScale = ContentScale.FillBounds

            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(7.dp))
       //imports from Sign Class
            val cont = Signs()
            Text(
                text = stringResource(id = R.string.greet2),
                color = Color.Red,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            cont.Textfield(detail = user, fieldname = "Email")

            Spacer(modifier = Modifier.height(14.dp))

            cont.Pass(password, showPassword, text="")

            Spacer(modifier = Modifier.height(35.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                cont.Buttons(onClick = {
                    val email = user.value
                    val password = password.value

                    // Validate user inputs
                    if (validation.validateLogin(email, password)) {
                        // Call a function to authenticate user against database
//                        if (authenticateUser(email, password))
//                        {

                        navController.navigate(Goto.DriverHome.route) {
                            popUpTo(Goto.Home.route) {
                                inclusive = true
                            }
                        }
//                        } else {
//                            // Handle case when authentication fails
//                            // Display an error message or redirect to the login screen
//                        }
                    }

                    else{
                        Toast.makeText(context,"Please fill correct email or password", Toast.LENGTH_SHORT).show()
                    }
                }, id = R.string.login)
                Spacer(modifier = Modifier.height(180.dp))
            }

        }

    }

}