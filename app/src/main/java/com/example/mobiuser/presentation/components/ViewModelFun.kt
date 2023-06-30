package com.example.mobiuser.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.mobiuser.Goto

class ViewModelFun {

    fun navigateNext(navController: NavController,route: String) {
        navController.navigate(route) {
            popUpTo(Goto.Login.route) {
                inclusive = true
            }
        }
    }
     fun showError(context: Context){
        Toast.makeText(context," An error occurred, please try again", Toast.LENGTH_SHORT).show()
    }
}