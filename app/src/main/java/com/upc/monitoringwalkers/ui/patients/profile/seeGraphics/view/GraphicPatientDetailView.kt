package com.upc.monitoringwalkers.ui.patients.profile.seeGraphics.view

import com.upc.monitoringwalkers.model.PointEntity

interface GraphicPatientDetailView{
    fun onFetchGraphicForceSuccess(pointEntity: PointEntity)

    fun onFetchGraphicSpeedSuccess(pointEntity: PointEntity)

    fun onFetchGraphicForceFail(error: String)

    fun onFetchGraphicSpeedFail(error: String)

}