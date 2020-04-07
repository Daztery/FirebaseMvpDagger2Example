package com.upc.monitoringwalkers.ui.doctor.listPatientsByTherapist.presenter

import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.doctor.listPatientsByTherapist.view.ListPatientsByTherapistView

interface ListPatientsByTherapistPresenter : BasePresenter<ListPatientsByTherapistView> {

    fun viewReady(doctorId: String)

    fun listAllPatientByDoctorAndFiltered(doctorId: String)

    fun onTherapistIdChanged(therapistId: String)

    fun onDeleteButtonClicked(patientEntity: PatientEntity)
}