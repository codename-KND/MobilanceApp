package com.example.mobiuser.data.remote.dto

data class LoginResponse(
    val token: String,
    val user_info: UserInfo
)