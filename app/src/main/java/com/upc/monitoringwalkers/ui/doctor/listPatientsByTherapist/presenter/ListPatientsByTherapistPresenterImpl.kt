package com.upc.monitoringwalkers.ui.doctor.listPatientsByTherapist.presenter

import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.model.isValid
import com.upc.monitoringwalkers.ui.doctor.listPatientsByTherapist.view.ListPatientsByTherapistView
import javax.inject.Inject

class ListPatientsByTheparistPresenterImpl @Inject constructor(
    private val databaseInterface: FirebaseDatabaseInterface
) : ListPatientsByTherapistPresenter {

    private lateinit var view: ListPatientsByTherapistView

    private lateinit var therapistId : String

    override fun setView(view: ListPatientsByTherapistView) {
        this.view = view
    }

    override fun listAllPatientByDoctorAndFiltered(doctorId: String) {

        databaseInterface.listenToPatientByDoctor(doctorId) {
            view.addPatientToTherapist(it)
        }
    }

    override fun onDeleteTherapistIdClicked(patientEntity: PatientEntity) {
        databaseInterface.getPatientProfile(patientEntity.id){
            if(it.isValid()){
                it.therapistId = patientEntity.therapistId
                databaseInterface.deleteTherapistFromPatient(it)
            } else {
                print("No existe este paciente!")
            }
        }

    }

    override fun viewReady(doctorId: String) {
        listAllPatientByDoctorAndFiltered(doctorId)
    }


}