package com.upc.monitoringwalkers.ui.patients.profile.seeGraphics.view

import com.upc.monitoringwalkers.model.PointEntity

interface GraphicPatientDetatilView{
    fun onFetchGraphicForceSuccess(pointEntity: PointEntity)

    fun onFetchGraphicSpeedSuccess(pointEntity: PointEntity)

    fun onFetchGraphicForceFail(error: String)

    fun onFetchGraphicSpeedFail(error: String)

}