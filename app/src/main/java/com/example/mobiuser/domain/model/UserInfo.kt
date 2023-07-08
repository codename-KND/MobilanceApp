package com.example.mobiuser.domain.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    val email: String,
    val id: Int,
    val username: String
)
data class request_id(
    @SerializedName("request_id")
    val request_id: Int)