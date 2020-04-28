package com.upc.monitoringwalkers.ui.therapists.profile.listPatients.presenter

import com.upc.monitoringwalkers.firebase.authentication.FirebaseAuthenticationInterface
import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.model.isValid
import com.upc.monitoringwalkers.ui.therapists.profile.listPatients.view.TherapistProfileView
import javax.inject.Inject

class TherapistProfilePresenterImpl @Inject constructor(
    private val authentication: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : TherapistProfilePresenter {

    private lateinit var view: TherapistProfileView

    override fun setView(view: TherapistProfileView) {
        this.view = view
    }
    override fun viewReady(therapistId: String) {
        fetchTherapistProfile(therapistId)
    }

    override fun listAllPatientByDoctorAndFiltered(doctorId: String) {

        databaseInterface.listPatientsByDoctor(doctorId) {
            view.addPatientToTherapist(it)
        }
    }
    override fun logout() {
        authentication.logout {
            view.logoutSuccess()
        }
    }

    override fun fetchTherapistProfile(therapistId: String) {
        databaseInterface.getTherapistProfile(therapistId){
            if(it.isValid()){
                listAllPatientByDoctorAndFiltered(it.doctorId)
                //view.onFetchTherapistProfileSuccess(it)
            } else {
                view.onFetchTherapistProfileFail("No existe este terapeuta!")
            }
        }
    }


}