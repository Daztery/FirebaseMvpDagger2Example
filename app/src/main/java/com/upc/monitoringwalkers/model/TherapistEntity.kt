package com.upc.monitoringwalkers.model

import android.os.Bundle


data class TherapistEntity(
    var id: String = "",
    var name: String = "",
    var lastName: String = "",
    var email: String = "",
    val type: UserType = UserType.THERAPIST,
    val specialty: String = "",
    var doctorId: String = ""
){
    companion object {
        fun from(bundle: Bundle): TherapistEntity {
            return TherapistEntity(
            )
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        with(bundle) {
            putString("id", id)
        }
        return bundle
    }
}

fun TherapistEntity.mapToTherapist() = TherapistEntity(id, name, lastName, email, type, specialty, doctorId)
fun TherapistEntity.isValid() = name.isNotBlank()
        && email.isNotBlank()
        && type.toString().isNotBlank() && type == UserType.THERAPIST