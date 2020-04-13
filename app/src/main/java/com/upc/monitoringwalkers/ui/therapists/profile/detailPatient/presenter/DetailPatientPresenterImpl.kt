package com.upc.monitoringwalkers.ui.therapists.profile.detailPatient.presenter

import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.model.isValid
import com.upc.monitoringwalkers.ui.therapists.profile.detailPatient.view.DetailPatientView
import javax.inject.Inject

class DetailPatientPresenterImpl @Inject constructor(
    private val databaseInterface: FirebaseDatabaseInterface
): DetailPatientPresenter {

    private lateinit var view: DetailPatientView

    override fun setView(view: DetailPatientView) {
        this.view=view
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



}