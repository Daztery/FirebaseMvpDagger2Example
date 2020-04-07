package com.upc.monitoringwalkers.ui.patients.profile.view

import com.upc.monitoringwalkers.model.PatientEntity

interface PatientProfileView{
    fun onFetchPatientProfileSuccess(patientEntity: PatientEntity)

    fun onFetchPatientProfileFail(error: String)

    fun logoutError()

    fun logoutSuccess()
}