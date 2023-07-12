package com.example.mobiuser.presentation.AcceptTrip

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobiuser.Goto

@Composable
fun ShowGlobalMapIntentUri(context: Context, mapIntentUri: String, navController: NavController) {
    if (mapIntentUri.isNotEmpty()) {
        val clickableText = "Press Here to see the trip on Google Maps"
        ClickableText(
            text = AnnotatedString(clickableText),modifier = Modifier.background(color = Color.Red),
            onClick = {
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapIntentUri))
                context.startActivity(webIntent)

            }
        )
        Spacer(modifier = Modifier.height(40.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text= "Hint: Only click End Trip when you complete the trip",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold)
            Button(onClick = { navController.navigate(Goto.EndTrip.route){
                launchSingleTop = true
                popUpTo(Goto.AcceptTrip.route) { saveState = true }
            } },) {
                Text(text = "End Trip")
            }

        }

    }
}
