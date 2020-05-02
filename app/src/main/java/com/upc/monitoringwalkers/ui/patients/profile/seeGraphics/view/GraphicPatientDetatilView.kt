package com.upc.monitoringwalkers.ui.patients.profile.seeGraphics.view

import com.upc.monitoringwalkers.model.PointEntity

interface GraphicPatientDetatilView{
    fun onFetchGraphicForceSuccess(points: ArrayList<PointEntity>)

    fun onFetchGraphicSpeedSuccess(points: PointEntity)

    fun onFetchGraphicForceFail(error: String)

    fun onFetchGraphicSpeedFail(error: String)

}