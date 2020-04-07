package com.upc.monitoringwalkers.ui.therapists.profile.presenter

import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.therapists.profile.view.TherapistProfileView

interface TherapistProfilePresenter: BasePresenter<TherapistProfileView> {
    fun viewReady(therapistId: String)

    fun logout()

    fun fetchTherapistProfile(therapistId: String)
}