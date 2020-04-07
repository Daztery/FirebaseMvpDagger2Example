package com.upc.monitoringwalkers.ui.doctor.listPatientsWithoutTherapist.presenter

import android.content.Intent
import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.model.isValid
import com.upc.monitoringwalkers.ui.doctor.detailTherapist.DetailTherapistActivity
import com.upc.monitoringwalkers.ui.doctor.listPatientsWithoutTherapist.view.ListPatientsWithoutTherapistView
import javax.inject.Inject

class ListPatientsWithoutTheparistPresenterImpl @Inject constructor(
    private val databaseInterface: FirebaseDatabaseInterface
) : ListPatientsWithoutTherapistPresenter {

    private lateinit var view: ListPatientsWithoutTherapistView

    override fun setView(view: ListPatientsWithoutTherapistView) {
        this.view = view
    }

    override fun listAllPatientWithoutTherapist(doctorId: String) {
        databaseInterface.listenToPatientByDoctor(doctorId) {
            view.addPatientWithoutTherapistToList(it)
        }
    }

    override fun onTherapistIdChanged(patientEntity: PatientEntity) {
        databaseInterface.getPatientProfile(patientEntity.id){
            if(it.isValid()){
                it.therapistId = patientEntity.therapistId
                databaseInterface.updatePatientWithTherapist(it)

            } else {
               print("No existe este paciente!")
            }
        }
    }

    override fun viewReady(doctorId: String) {
        listAllPatientWithoutTherapist(doctorId)
    }


}