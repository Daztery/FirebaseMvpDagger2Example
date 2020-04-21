package com.upc.monitoringwalkers.ui.therapists.profile.detailPatient.presenter

import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.therapists.profile.detailPatient.view.DetailPatientView

interface DetailPatientInTherapistPresenter:BasePresenter<DetailPatientView> {
    fun fetchPatientProfile(patientId: String)
}