package com.example.mobiuser.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



class appRes {


    @Composable
    fun infocard(image: Int, desc: Int, onClick:()->Unit){


            Button(
                onClick = onClick,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(0.99f)
                    .padding(7.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Gray.copy(alpha = 0.75f))
            ) {

                Image(
                    painter = painterResource(id = image), contentDescription = null,
                    modifier = Modifier
                        .height(170.dp)
                        .width(170.dp)
                        .padding(7.dp)
                        .clip(RoundedCornerShape(7.dp)),

                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = stringResource(id = desc),
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontFamily = FontFamily.Serif
                )
            }


    }
    @Composable
    fun words(id:Int){
        Text(text = stringResource(id = id), color = Color.White,
            fontSize =20.sp, fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif )
    }
    @Composable
    fun details(info:String){
        Text(text = "$info", color = Color.White,
            fontSize =15.sp,
            fontFamily = FontFamily.Serif )
    }






}