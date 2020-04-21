package com.upc.monitoringwalkers.ui.doctor.addTherapist.presenter

import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.model.CommentEntity
import com.upc.monitoringwalkers.model.RegisterCommentModel
import com.upc.monitoringwalkers.ui.therapists.profile.addComment.presenter.AddCommentPresenter
import com.upc.monitoringwalkers.ui.therapists.profile.addComment.view.AddCommentView
import java.util.*
import javax.inject.Inject

class AddCommentPresenterImpl @Inject constructor(
    private val databaseInterface: FirebaseDatabaseInterface
) : AddCommentPresenter{


    private lateinit var view: AddCommentView

    private val commentModel = RegisterCommentModel()

    override fun setView(view: AddCommentView) {
        this.view = view
    }

    override fun onTextChanged(text: String) {
        commentModel.comment = text
    }

    override fun onDateChanged(date: String) {
        commentModel.date = date
    }

    override fun onTherapistIdChanged(therapistId: String) {
        commentModel.therapistId = therapistId
    }

    override fun onPatientChanged(patientId: String) {
        commentModel.patientId = patientId
    }


    override fun onRegisterClicked(therapistId: String) {

        val commentEntity = CommentEntity()
        commentEntity.comment=commentModel.comment
        commentEntity.date=commentModel.date
        commentEntity.therapistId=commentModel.therapistId
        commentEntity.patientId=commentModel.patientId
        commentEntity.id = UUID.randomUUID().toString()
        if (commentModel.isValid()) {
            databaseInterface.createComment(commentEntity)
        } else {
            view.showSignUpError()
        }
    }

}