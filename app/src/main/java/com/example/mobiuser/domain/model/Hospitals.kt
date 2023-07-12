package com.example.mobiuser.domain.model

data class Hospital(
    val name: String,
    val latitude: Double,
    val longitude: Double
)

val hospitals: List<Hospital> = listOf(
//    Hospital("Kenyatta National Hospital", -1.3013, 36.7991),
//    Hospital("Aga Khan University Hospital", -1.2619, 36.7913),
//    Hospital("Nairobi Hospital", -1.2921, 36.8156),
//    Hospital("M.P. Shah Hospital", -1.2775, 36.8057),
//    Hospital("Kenyatta University Hospital", -1.1905, 36.9220),
//    Hospital("Gertrude's Children's Hospital", -1.2707, 36.8129),
//    Hospital("Coptic Hospital", -1.2734, 36.8061)
    Hospital("Gertrude's Children's Hospital", -1.2561409, 36.8322306),
    Hospital("Nairobi Hospital", -1.296080, 36.808670),
    Hospital("Aga Khan University Hospital", -1.2618007, 36.8238621),
    Hospital("MP Shah Hospital", -1.2635987347970226, 36.811996532869614),
    Hospital("Kenyatta National Hospital", -1.3008309, 36.8072359),
    Hospital("Avenue Hospital", -1.2644781, 36.8176407),
    Hospital("Mater Hospital", -1.3076000077466186,36.83398873176935),
    Hospital("Coptic Hospital", -1.2982482597318956,36.797769453559965),
    Hospital("Nairobi West Hospital", -1.3064579, 36.8259748),
    Hospital("Karen Hospital", -1.336, 36.7261209),
)