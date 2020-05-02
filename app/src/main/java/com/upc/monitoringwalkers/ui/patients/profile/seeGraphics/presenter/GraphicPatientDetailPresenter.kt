package com.upc.monitoringwalkers.ui.patients.profile.seeGraphics.presenter

import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.patients.profile.seeGraphics.view.GraphicPatientDetailView

interface GraphicPatientDetailPresenter : BasePresenter<GraphicPatientDetailView>{

    fun viewReady(patientId: String)

    fun fetchPointsOfSpeed(patientId: String)

    fun fetchPointsOfForce(patientId: String)
}