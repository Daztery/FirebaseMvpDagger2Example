package com.upc.monitoringwalkers.model

enum class UserType {
    DOCTOR, // can add patients and therapist
    THERAPIST, // have many patients and make comments for himself or herself (TODO)
    PATIENT, // only can view info
    ADMIN // can add doctors
}
