package com.upc.monitoringwalkers.ui.therapists.profile.view

import com.upc.monitoringwalkers.model.TherapistEntity

interface TherapistProfileView {
    fun onFetchTherapistProfileSuccess(therapistEntity: TherapistEntity)

    fun onFetchTherapistProfileFail(error: String)

    fun logoutError()

    fun logoutSuccess()
}