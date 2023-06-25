package com.example.mobiuser.ui.theme.driversUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import com.example.mobiuser.ui.theme.components.appRes
import com.example.mobiuser.R

@Composable
fun driverHome(navController: NavController){

    val cards = appRes()

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize(1f))
    {
        Column(modifier = Modifier.padding(7.dp)) {
            cards.words(id = R.string.greet2)
            Spacer(modifier = Modifier.height(32.dp))
            cards.infocard(image = R.drawable.ambulancemain, desc = R.string.available, onClick = {navController.navigate(
                Goto.Request.route)})
            cards.infocard(image = R.drawable.laun, desc = R.string.History, onClick = {})
        }
    }
}

