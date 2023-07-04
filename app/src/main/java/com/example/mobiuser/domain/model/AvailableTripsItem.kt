package com.example.mobiuser.domain.model

data class AvailableTripsItem(
    val contact: String,
    val hospitalLatitude: String,
    val hospitalLongitude: String,
    val patient: String,
    val pickLatitude: String,
    val pickLongitude: String,
    val request_id: Int
)