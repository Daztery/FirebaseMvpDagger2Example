package com.upc.monitoringwalkers.ui.doctor.addPacient.view

interface AddTherapistView {

    fun onRegisterSuccess()

    fun showSignUpError()

    fun showEmailError()

    fun showPasswordError()

    fun showPasswordMatchingError()

}