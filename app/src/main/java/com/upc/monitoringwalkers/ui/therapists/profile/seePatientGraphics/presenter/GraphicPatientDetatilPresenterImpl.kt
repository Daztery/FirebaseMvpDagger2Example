package com.upc.monitoringwalkers.ui.therapists.profile.seePatientGraphics.presenter

import com.upc.monitoringwalkers.firebase.authentication.FirebaseAuthenticationInterface
import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.model.isValid
import com.upc.monitoringwalkers.ui.therapists.profile.seePatientGraphics.view.GraphicPatientDetatilView
import javax.inject.Inject

class GraphicPatientDetatilPresenterImpl @Inject constructor(
    private val authentication: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : GraphicPatientDetatilPresenter {


    private lateinit var view: GraphicPatientDetatilView

    override fun setView(view: GraphicPatientDetatilView) {
        this.view = view
    }

    override fun fetchPointsOfForce(patientId: String) {
        databaseInterface.getOfForceGraphicByPatient(patientId){
            if(it.isValid()){
                view.onFetchGraphicForceSuccess(it)
            } else {
                view.onFetchGraphicForceFail("No tiene datos!")
            }
        }
    }

    override fun fetchPointsOfSpeed(patientId: String) {
        databaseInterface.getOfSpeedGraphicByPatient(patientId){
            if(it.isValid()){
                view.onFetchGraphicSpeedSuccess(it)
            } else {
                view.onFetchGraphicSpeedFail("No tiene datos!")
            }
        }
    }

    override fun viewReady(patientId: String) {
        fetchPointsOfForce(patientId)
        fetchPointsOfSpeed(patientId)
    }


}