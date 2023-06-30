package com.example.mobiuser.domain.model

import com.google.gson.annotations.SerializedName



data class SignUpRequest(
    val username: String,
    val password: String,
    val email: String
)
data class SignUpResponse(
@SerializedName("user_info")
val userInfo: UserInfo?,
@SerializedName("token")
val token: String?,
@SerializedName("non_field_errors")
val errors: List<String>?
) {
    data class UserInfo(
        @SerializedName("id")
        val id: Int,
        @SerializedName("username")
        val username: String,
        @SerializedName("email")
        val email: String
    )
}