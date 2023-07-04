package com.example.mobiuser.presentation.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import com.example.mobiuser.presentation.components.Signs
import com.example.mobiuser.R


@Composable
fun Launch(navController: NavController) {

    val logo = painterResource(id = R.drawable.ambulancemain)

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Color.Black),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(7.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column() {
            Image(
                painter = logo, contentDescription = null,
                modifier = Modifier
                    .height(250.dp)
                    .clip(shape = RoundedCornerShape(7.dp))
                    .width(300.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(46.dp))
            Text(
                text = stringResource(id = R.string.mobi),
                color = Color.Red,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.welcome),
                color = Color.LightGray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(56.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                val signs = Signs()
                signs.Buttons(onClick = { navController.navigate(Goto.DriverLogin.route) }, id = R.string.login)
                Spacer(modifier = Modifier.width(86.dp))
                signs.Buttons(onClick = {navController.navigate(Goto.SignUp.route)}, id = R.string.sign)

            }
        }

    }}

}
