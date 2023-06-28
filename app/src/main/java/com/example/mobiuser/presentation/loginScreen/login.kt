package com.example.mobiuser.presentation.loginScreen

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import com.example.mobiuser.R
import com.example.mobiuser.presentation.loginScreen.LoginViewModel
import com.example.mobiuser.presentation.components.Signs
import com.example.mobiuser.presentation.components.Validator



@Composable
///user login page

fun Login(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val logo = painterResource(id = R.drawable.login)
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

            Spacer(modifier = Modifier.height(16.dp))
        //imports from Sign Class
            val cont = Signs()

            cont.Textfield(detail = user, fieldname = "Username")

            Spacer(modifier = Modifier.height(16.dp))

            cont.Pass(password, showPassword, text="")

            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                cont.Buttons(onClick = {
                    val user = user.value
                    val password = password.value

//                     Validate user inputs
                    if (validation.isPasswordValid(password)) {
                        // Call a function to authenticate user against database
                        loginViewModel.onClick(user, password,navController)

                    }

                  else{
                        Toast.makeText(context,"Please fill correct username or password", Toast.LENGTH_SHORT).show()
                         }
                }, id = R.string.login)
            }
            Spacer(modifier = Modifier.height(128.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.noAcc),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(16.dp))
                cont.Buttons(onClick = {navController.navigate(Goto.SignUp.route)}, id = R.string.sign)
            }
        }

    }

}






