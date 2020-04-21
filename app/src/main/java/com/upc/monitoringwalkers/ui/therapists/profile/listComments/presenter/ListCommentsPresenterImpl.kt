package com.upc.monitoringwalkers.ui.therapists.profile.listComments.presenter

import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.model.CommentEntity
import com.upc.monitoringwalkers.ui.therapists.profile.listComments.view.ListCommentsView
import javax.inject.Inject

class ListCommentsPresenterImpl @Inject constructor(
    private val databaseInterface: FirebaseDatabaseInterface
) : ListCommentsPresenter {

    private lateinit var view: ListCommentsView

    override fun setView(view: ListCommentsView) {
        this.view = view
    }

    override fun viewReady(patientId: String) {
        listAllCommentsByPatient(patientId)
    }

    override fun listAllCommentsByPatient(patientId: String) {
        databaseInterface.listCommentsByPatient(patientId) {
            view.addCommentToList(it)
        }
    }

    override fun onDeleteButtonClicked(commentEntity: CommentEntity) {
       /* databaseInterface.deleteUser(patientEntity.id) {
            view.deletePatientFromList(patientEntity)
        }*/
        print("Eliminado")
    }
}