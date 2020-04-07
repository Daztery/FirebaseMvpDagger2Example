package com.upc.monitoringwalkers.ui.patients.profile.presenter

import com.upc.monitoringwalkers.firebase.authentication.FirebaseAuthenticationInterface
import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.model.isValid
import com.upc.monitoringwalkers.ui.patients.profile.view.PatientProfileView
import javax.inject.Inject

class PatientProfilePresenterImpl @Inject constructor(
    private val authentication: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : PatientProfilePresenter {


    private lateinit var view: PatientProfileView

    override fun setView(view: PatientProfileView) {
        this.view = view
    }

    override fun fetchPatientProfile(patientId: String) {
        databaseInterface.getPatientProfile(patientId){
            if(it.isValid()){
                view.onFetchPatientProfileSuccess(it)
            } else {
                view.onFetchPatientProfileFail("No existe este paciente!")
            }
        }
    }

    override fun viewReady(patientId: String) {
        print("Inicio de sesion")
    }

    override fun logout() {
        authentication.logout {
            view.logoutSuccess()
        }
    }

}