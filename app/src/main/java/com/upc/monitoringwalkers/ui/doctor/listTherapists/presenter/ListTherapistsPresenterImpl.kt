package com.upc.monitoringwalkers.ui.doctor.listTherapists.presenter

import com.upc.monitoringwalkers.firebase.authentication.FirebaseAuthenticationInterface
import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.model.TherapistEntity
import com.upc.monitoringwalkers.ui.doctor.listTherapists.view.ListTherapistsView
import javax.inject.Inject

class ListTherapistsPresenterImpl @Inject constructor(
    private val databaseInterface: FirebaseDatabaseInterface
) : ListTherapistsPresenter {

    private lateinit var view: ListTherapistsView

    override fun setView(view: ListTherapistsView) {
        this.view = view
    }


    override fun viewReady(doctorId: String) {
        listAllTherapistByDoctor(doctorId)
    }

    override fun listAllTherapistByDoctor(doctorId: String) {
        databaseInterface.listenToTherapistByDoctor(doctorId) {
            view.addTherapist(it)
        }
    }


    override fun onDeleteButtonClicked(therapistEntity: TherapistEntity) {
        databaseInterface.deleteUser(therapistEntity.id) {
            view.deleteTherapist(therapistEntity)
        }
    }
}