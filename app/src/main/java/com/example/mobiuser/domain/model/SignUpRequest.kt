package com.example.mobiuser.domain.model

import com.squareup.moshi.Json


data class SignUpRequest(
    val username: String,
    val password: String,
    val email: String
)
data class SignUpResponse(
    @field:Json(name = "username") val username: List<String>?,
    @field:Json(name = "password") val password: List<String>?,
    @field:Json(name = "email") val email: List<String>?
)