package com.example.mobiuser.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

class Signs {
    @Composable
    fun Pass(password: MutableState<String>, showPassword: MutableState<Boolean>, text:String?) {
        val passwordTransformation = if (showPassword.value) VisualTransformation.None
        else PasswordVisualTransformation()

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = "$text Your Password", color = Color.White) },
            modifier = Modifier
                .fillMaxWidth(1f),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        Icons.Default.KeyboardArrowLeft,
                        contentDescription = if (showPassword.value) "Hide password" else "Show password"
                    )
                }
            },
            visualTransformation = passwordTransformation
        )
    }
    @Composable
    fun Textfield(detail: MutableState<String>,fieldname: String){

        OutlinedTextField(
            value = detail.value,
            onValueChange = { detail.value = it },
            label = { Text(text = "$fieldname", color = Color.White) },
            modifier = Modifier.fillMaxWidth(1f),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                //contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
        )

    }
    @Composable
    fun Buttons(onClick: () -> Unit, id:Int){

        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            shape = RoundedCornerShape(7.dp)
        ) {
            Text(text = stringResource(id = id), color = Color.White)
        }
    }

    @Composable
    fun EmailField(email: MutableState<String>) {
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text(text = "Email",color = Color.White) },
            placeholder = { Text(text = "Enter your email",color = Color.White) },
            modifier = Modifier.fillMaxWidth(1f),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email

            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                //contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
        )
    }
}
