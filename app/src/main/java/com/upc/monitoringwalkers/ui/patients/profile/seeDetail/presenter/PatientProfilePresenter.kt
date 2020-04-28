package com.upc.monitoringwalkers.ui.patients.profile.seeDetail.presenter

import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.patients.profile.seeDetail.view.PatientProfileView

interface PatientProfilePresenter : BasePresenter<PatientProfileView>{

    fun viewReady(patientId: String)

    fun logout()

    fun fetchPatientProfile(patientId: String)
}