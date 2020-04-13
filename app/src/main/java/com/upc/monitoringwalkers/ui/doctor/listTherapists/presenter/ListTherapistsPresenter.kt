package com.upc.monitoringwalkers.ui.doctor.listTherapists.presenter
import com.upc.monitoringwalkers.model.TherapistEntity
import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.doctor.listTherapists.view.ListTherapistsView

interface ListTherapistsPresenter : BasePresenter<ListTherapistsView> {

    fun viewReady(doctorId: String)

    fun listAllTherapistByDoctor(doctorId: String)

    fun onDeleteButtonClicked(therapistEntity: TherapistEntity)

    fun deleteAllPatientsHaveTherapistId(doctorId: String)
}