package com.upc.monitoringwalkers.ui.doctor.addTherapist.view

interface AddTherapistView {

    fun onRegisterSuccess()

    fun showSignUpError()

    fun showEmailError()

    fun showPasswordError()

    fun showPasswordMatchingError()

}