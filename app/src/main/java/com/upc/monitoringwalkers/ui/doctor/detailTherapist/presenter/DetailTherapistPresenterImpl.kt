package com.upc.monitoringwalkers.ui.doctor.detailTherapist.presenter

import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.model.isValid
import com.upc.monitoringwalkers.ui.doctor.detailTherapist.view.DetailTherapistView
import javax.inject.Inject

class DetailTherapistPresenterImpl @Inject constructor(
    private val databaseInterface: FirebaseDatabaseInterface
): DetailTherapistPresenter {

    private lateinit var view:DetailTherapistView

    override fun setView(view: DetailTherapistView) {
        this.view=view
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



}