package com.example.mobiuser.domain.model

data class Hospital(
    val name: String,
    val latitude: Double,
    val longitude: Double
)

val hospitals: List<Hospital> = listOf(
    Hospital("Kenyatta National Hospital", -1.3013, 36.7991),
    Hospital("Aga Khan University Hospital", -1.2619, 36.7913),
    Hospital("Nairobi Hospital", -1.2921, 36.8156),
    Hospital("M.P. Shah Hospital", -1.2775, 36.8057),
    Hospital("Kenyatta University Hospital", -1.1905, 36.9220),
    Hospital("Gertrude's Children's Hospital", -1.2707, 36.8129),
    Hospital("Coptic Hospital", -1.2734, 36.8061)
)