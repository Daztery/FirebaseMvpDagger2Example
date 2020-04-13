package com.upc.monitoringwalkers.ui.therapists.profile.listPatients.view

import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.model.TherapistEntity

interface TherapistProfileView {

    fun logoutError()

    fun logoutSuccess()

    fun addPatientToTherapist(patientEntity: PatientEntity)

    fun onFetchTherapistProfileSuccess(therapistEntity: TherapistEntity)

    fun onFetchTherapistProfileFail(error: String)

}