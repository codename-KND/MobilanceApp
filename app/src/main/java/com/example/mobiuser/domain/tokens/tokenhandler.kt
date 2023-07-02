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

}