package com.upc.monitoringwalkers.model

import com.upc.monitoringwalkers.common.arePasswordsSame
import com.upc.monitoringwalkers.common.isEmailValid

data class RegisterPatientModel(
    var name: String = "",
    var lastName: String = "",
    var email: String = "",
    val type: UserType = UserType.PATIENT,
    var password: String = "",
    var repeatPassword: String = "",
    var age: String = "0",
    var weight: String = "0",
    var affectation: Affectation = Affectation.LOW,
    var treatment: String = "",
    var doctorId: String = "",
    var therapistId: String = ""
) {
    fun isValid(): Boolean = isEmailValid(email) && arePasswordsSame(
        password,
        repeatPassword
    ) && doctorId.isNotBlank() && name.isNotEmpty() && lastName.isNotEmpty()
            && age.isNotBlank() && weight.isNotBlank() && treatment.isNotBlank()
}


data class RegisterDoctorModel(
    var name: String = "",
    var lastName: String = "",
    var email: String = "",
    val type: UserType = UserType.DOCTOR,
    var password: String = "",
    var repeatPassword: String = ""
) {
    fun isValid(): Boolean =
        isEmailValid(email) && arePasswordsSame(password, repeatPassword) && name.isNotEmpty() && lastName.isNotEmpty()
}

data class RegisterTherapistModel(
    var name: String = "",
    var lastName: String = "",
    var email: String = "",
    val type: UserType = UserType.THERAPIST,
    var specialty: String = "",
    var password: String = "",
    var repeatPassword: String = "",
    var doctorId: String = ""
) {
    fun isValid(): Boolean =
        isEmailValid(email) && arePasswordsSame(password, repeatPassword) && name.isNotEmpty() && lastName.isNotEmpty()
                && specialty.isNotEmpty()
}

data class RegisterCommentModel(
    var id: String = "",
    var patientId: String = "",
    var therapistId: String = "",
    var comment: String = "",
    var date: String = ""
) {
    fun isValid(): Boolean = comment.isNotBlank()
}
