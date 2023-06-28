package com.example.mobiuser.domain.model

data class LoginResponse(
    val token: String,
    val user_info: UserInfo
)