package com.upc.monitoringwalkers.ui.doctor.detailTherapist.view

import com.upc.monitoringwalkers.model.TherapistEntity

interface DetailTherapistView {
    fun onFetchTherapistProfileSuccess(therapistEntity: TherapistEntity)
    fun onFetchTherapistProfileFail(error: String)
}