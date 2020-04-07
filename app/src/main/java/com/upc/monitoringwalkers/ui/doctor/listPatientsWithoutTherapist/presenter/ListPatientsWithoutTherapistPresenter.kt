package com.upc.monitoringwalkers.ui.doctor.listPatientsWithoutTherapist.presenter

import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.doctor.listPatientsWithoutTherapist.view.ListPatientsWithoutTherapistView

interface ListPatientsWithoutTherapistPresenter : BasePresenter<ListPatientsWithoutTherapistView> {

    fun viewReady(doctorId: String)

    fun listAllPatientWithoutTherapist(doctorId: String)

    fun onTherapistIdChanged(patientEntity: PatientEntity)

}