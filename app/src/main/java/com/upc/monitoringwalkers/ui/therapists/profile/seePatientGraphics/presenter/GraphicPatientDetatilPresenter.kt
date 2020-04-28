package com.upc.monitoringwalkers.ui.therapists.profile.seePatientGraphics.presenter

import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.patients.profile.seeGraphics.view.GraphicPatientDetatilView

interface GraphicPatientDetatilPresenter : BasePresenter<GraphicPatientDetatilView>{

    fun viewReady(patientId: String)

    fun fetchPointsOfSpeed(patientId: String)

    fun fetchPointsOfForce(patientId: String)
}