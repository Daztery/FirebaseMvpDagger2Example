package com.upc.monitoringwalkers.ui.doctor.choosePatientOrTherapist.presenter

import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.doctor.choosePatientOrTherapist.view.ChoosePatientOrTherapistView

interface ChoosePatientOrTherapistPresenter : BasePresenter<ChoosePatientOrTherapistView> {

    fun viewReady(doctorId: String)

    fun logout()

}