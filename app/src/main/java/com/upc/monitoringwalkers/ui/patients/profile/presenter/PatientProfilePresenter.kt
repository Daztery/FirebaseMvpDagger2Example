package com.upc.monitoringwalkers.ui.patients.profile.presenter

import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.patients.profile.view.PatientProfileView

interface PatientProfilePresenter : BasePresenter<PatientProfileView>{

    fun viewReady(patientId: String)

    fun logout()

    fun fetchPatientProfile(patientId: String)
}