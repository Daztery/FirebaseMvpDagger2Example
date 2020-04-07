package com.upc.monitoringwalkers.ui.doctor.listPatients.view

import com.upc.monitoringwalkers.model.PatientEntity

interface ListPatientsView {

    fun showNoDataDescription()

    fun hideNoDataDescription()

    fun addPatientToList(patientEntity: PatientEntity)

    fun deletePatientFromList(patientEntity: PatientEntity)

}