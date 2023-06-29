package com.example.mobiuser.domain.model

import com.squareup.moshi.Json

data class RequestData(
   // @Json(name = "token") val token: String,
    @Json(name = "Patient") val patient: String,
    @Json(name = "PickLatitude") val pickLatitude: Double,
    @Json(name = "PickLongitude") val pickLongitude: Double,
    @Json(name = "Contact") val contact: String,
    @Json(name = "HospitalLatitude") val hospitalLatitude: Double,
    @Json(name = "HospitalLongitude") val hospitalLongitude: Double
)

data class ServerResponse(
    val request_id: Int,
    val patient: String,
    val distance: Double?
)
data class ErrorResponse(
    val patient: List<String>,
    val contact: List<String>
)
