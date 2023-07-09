package com.example.mobiuser.domain.tokens

import android.content.SharedPreferences
import javax.inject.Inject

class TokenHandler @Inject constructor(
    private val sharedPreferences: SharedPreferences
){

    fun storeToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }
    fun getToken(): String {
        return sharedPreferences.getString("token", "") ?: ""
    }
    fun storeRequestID(requestID: String){
        val editor =sharedPreferences.edit()
        editor.putString("requestID", requestID)
        editor.apply()
    }
    fun getRequestID(): String {
        return sharedPreferences.getString("requestID", "") ?:"" }

    fun storePendingID(requestID: String){
        val editor =sharedPreferences.edit()
        editor.putString("pendingID", requestID)
        editor.apply()
    }
    fun getPendingID(): String {
        return sharedPreferences.getString("pendingID", "") ?: ""}


}