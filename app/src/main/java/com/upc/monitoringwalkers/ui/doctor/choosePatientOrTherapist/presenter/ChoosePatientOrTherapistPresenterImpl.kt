package com.upc.monitoringwalkers.ui.doctor.choosePatientOrTherapist.presenter

import com.upc.monitoringwalkers.firebase.authentication.FirebaseAuthenticationInterface
import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.ui.doctor.choosePatientOrTherapist.view.ChoosePatientOrTherapistView
import javax.inject.Inject

class ChoosePatientOrTherapistPresenterImpl @Inject constructor(
    private val authentication: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : ChoosePatientOrTherapistPresenter {

    private lateinit var view: ChoosePatientOrTherapistView

    override fun viewReady(doctorId: String) {
        print("Elige entre doctor o terapeuta")
    }

    override fun logout() {
        authentication.logout {
            view.logoutSuccess()
        }
    }

    override fun setView(view: ChoosePatientOrTherapistView) {
        this.view=view
    }
}