package com.upc.monitoringwalkers.ui.therapists.profile.listPatients.presenter

import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.therapists.profile.listPatients.view.TherapistProfileView

interface TherapistProfilePresenter: BasePresenter<TherapistProfileView> {
    fun viewReady(doctorId: String)

    fun logout()

    fun listAllPatientByDoctorAndFiltered(doctorId: String)

    fun fetchTherapistProfile(therapistId: String)

}