package com.upc.monitoringwalkers.ui.therapists.profile.presenter

import com.upc.monitoringwalkers.firebase.authentication.FirebaseAuthenticationInterface
import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.model.isValid
import com.upc.monitoringwalkers.ui.therapists.profile.view.TherapistProfileView
import javax.inject.Inject

class TherapistProfilePresenterImpl @Inject constructor(
    private val authentication: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : TherapistProfilePresenter{

    private lateinit var view: TherapistProfileView

    override fun viewReady(therapistId: String) {
        print("Inicio de sesion")
    }

    override fun logout() {
        authentication.logout {
            view.logoutSuccess()
        }
    }

    override fun fetchTherapistProfile(therapistId: String) {
        databaseInterface.getTherapistProfile(therapistId){
            if(it.isValid()){
                view.onFetchTherapistProfileSuccess(it)
            } else {
                view.onFetchTherapistProfileFail("No existe este terapeuta!")
            }
        }
    }

    override fun setView(view: TherapistProfileView) {
        this.view = view
    }
}