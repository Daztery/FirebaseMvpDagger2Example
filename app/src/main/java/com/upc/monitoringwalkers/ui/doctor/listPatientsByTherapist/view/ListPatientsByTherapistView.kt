package com.upc.monitoringwalkers.ui.doctor.listPatientsByTherapist.view

import com.upc.monitoringwalkers.model.PatientEntity

interface ListPatientsByTherapistView {

    fun showNoDataDescription()

    fun hideNoDataDescription()

    fun addPatientToTherapist(patientEntity: PatientEntity)

    fun deletePatientToTherapist(therapistId: String)

}