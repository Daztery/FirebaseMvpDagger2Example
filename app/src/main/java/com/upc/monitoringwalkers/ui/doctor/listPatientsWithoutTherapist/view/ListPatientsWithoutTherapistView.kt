package com.upc.monitoringwalkers.ui.doctor.listPatientsWithoutTherapist.view

import com.upc.monitoringwalkers.model.PatientEntity

interface ListPatientsWithoutTherapistView {

    fun showNoDataDescription()

    fun hideNoDataDescription()

    fun addPatientWithoutTherapistToList(patientEntity: PatientEntity)

    fun deletePatientWithoutTherapistFromList(patientEntity: PatientEntity)

}