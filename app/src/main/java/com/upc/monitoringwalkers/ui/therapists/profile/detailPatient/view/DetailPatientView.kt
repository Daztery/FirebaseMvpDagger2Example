package com.upc.monitoringwalkers.ui.therapists.profile.detailPatient.view

import com.upc.monitoringwalkers.model.PatientEntity


interface DetailPatientView {
    fun onFetchPatientProfileSuccess(patientEntity: PatientEntity)
    fun onFetchPatientProfileFail(error: String)
}