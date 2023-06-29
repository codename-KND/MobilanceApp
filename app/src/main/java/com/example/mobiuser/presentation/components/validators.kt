package com.example.mobiuser.presentation.components

import android.content.Context
import android.widget.Toast

class Validator(){

    //email password validator
    fun validateLogin(email: String, password: String): Boolean {

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val isEmailValid = email.matches(emailPattern.toRegex())
        val isPasswordValid = password.length >= 8 && !password.trim().isBlank()

        return isEmailValid && isPasswordValid
    }
    // password match validator

    fun validatePass(password1: String, password2: String,context: Context): Boolean {
        val isMatch = checkPasswordMatch(password1, password2)
        val isStrong = isPasswordValid(password1)

        if(!isStrong){
            Toast.makeText(context," Please input a strong password", Toast.LENGTH_SHORT).show()
        }
        else if(!isMatch){
           Toast.makeText(context," Please ensure the passwords match", Toast.LENGTH_SHORT).show()
        }


        return isMatch && isStrong
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$"
        return password.matches(passwordRegex.toRegex())
    }

    fun checkPasswordMatch(password1: String, password2: String): Boolean {
        return password1 == password2
    }
    //check for missing values in form

    fun checkFields(context: Context,vararg values: String): Boolean {
        for (value in values) {
            if (value.isNullOrEmpty()) {
                Toast.makeText(context,"Please fill in all the fields correctly from cf", Toast.LENGTH_SHORT).show()
                return false // Missing value found
            }
        }
        return true // No missing values found
    }
    fun validNumber(number: String): Boolean {
        val regex = Regex("^(\\+254|0)\\d{9}\$")

        return regex.matches(number)
    }
    fun checkForm(
        context: Context,
        contact: String,
        name: String,
        location: String,
        hospital: String ): Boolean {
        val full = checkFields(context, contact, name, location,hospital)
        val valid =validNumber(number = contact)

        if(!full){
            Toast.makeText(context," Please fill in the fields", Toast.LENGTH_SHORT).show()
        }
        else if(!valid){
            Toast.makeText(context," Please input a valid Phone number", Toast.LENGTH_SHORT).show()
        }
        return full && valid
    }
    fun checkSignUp(context: Context,email: String,username:String,password1:String,password2: String): Boolean {

        val full =checkFields(context,email,username,password1,password2)
        val passCheck = validatePass(password1,password2, context)
        val logCheck = validateLogin(email,password1)

        if(!full){
            Toast.makeText(context," Please fill in the fields", Toast.LENGTH_SHORT).show()
        }
        else if(!logCheck){
            Toast.makeText(context," Please fill in a valid email and password", Toast.LENGTH_SHORT).show()
        }

        return full && passCheck && logCheck
    }
    fun checkLogin(context: Context, username: String,password: String): Boolean{

        val isUsername = username.length>0&& !username.trim().isBlank()
        val isPasswordValid = password.length >= 8 && !password.trim().isBlank()

        return  isUsername && isPasswordValid
    }





}