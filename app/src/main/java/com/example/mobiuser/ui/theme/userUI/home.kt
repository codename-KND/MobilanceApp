package com.example.mobiuser.ui.theme.userUI


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import com.example.mobiuser.R
import com.example.mobiuser.presentation.components.appRes


@Composable
fun Home (navController: NavController){

    val cards = appRes()

    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceAround,
    modifier = Modifier
        .background(color = Color.Black)
        .fillMaxSize(1f))
    {
        Column(modifier = Modifier.padding(7.dp)) {
            cards.words(id = R.string.greet)
            Spacer(modifier = Modifier.height(32.dp))
            cards.infocard(image = R.drawable.ambulancemain, desc = R.string.request, onClick = {navController.navigate(
                Goto.Request.route)})
            //cards.infocard(image = R.drawable.twodocs, desc = R.string.contacts, onClick = {})
            cards.infocard(image = R.drawable.laun, desc = R.string.History, onClick = {})
        }
    }
}