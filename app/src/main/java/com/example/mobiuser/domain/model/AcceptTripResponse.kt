package com.example.mobiuser.domain.model

data class AcceptTripResponse(
    val driver: String,
    val pending_id: Int,
    val request: String,
    val status: Boolean
)