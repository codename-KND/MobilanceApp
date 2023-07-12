package com.example.mobiuser.presentation.CompleteTrip

import androidx.compose.animation.animateColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobiuser.Goto
import kotlinx.coroutines.delay


@Composable
fun EndTrip(
    navController: NavController,
    completeTrip: CompleteTripViewModel = hiltViewModel(),


){

    LaunchedEffect(Unit){
        completeTrip.completeTrip()
    }

    Scaffold(
        content = {
            CongratulationsScreen(navController)
        }
    )

}




@Composable
fun CongratulationsScreen(navController: NavController) {
    var countDown by remember { mutableStateOf(10) }

    LaunchedEffect(true) {
        do {
            delay(1000)
            countDown--
        } while (countDown > 0)
        navController.popBackStack(navController.graph.startDestinationId, inclusive = true)
        navController.navigate(Goto.DriverHome.route)
}

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Congratulations! Trip Successfully Completed",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "$countDown",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        )
    }
}