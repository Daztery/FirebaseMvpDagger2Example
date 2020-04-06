package com.upc.monitoringwalkers.ui.doctor.detailTherapist.presenter

import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.doctor.detailTherapist.view.DetailTherapistView

interface DetailTherapistPresenter:BasePresenter<DetailTherapistView> {
    fun fetchTherapistProfile(therapistId: String)
}