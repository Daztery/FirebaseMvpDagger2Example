package com.upc.monitoringwalkers.ui.doctor.detailPatient.presenter

import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.doctor.detailPatient.view.DetailPatientView

interface DetailPatientInTherapistPresenter:BasePresenter<DetailPatientView> {
    fun fetchPatientProfile(patientId: String)
}