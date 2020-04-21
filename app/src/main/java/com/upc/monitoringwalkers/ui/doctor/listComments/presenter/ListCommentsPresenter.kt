package com.upc.monitoringwalkers.ui.doctor.listComments.presenter

import com.upc.monitoringwalkers.model.CommentEntity
import com.upc.monitoringwalkers.ui.base.BasePresenter
import com.upc.monitoringwalkers.ui.doctor.listComments.view.ListCommentsView

interface ListCommentsPresenter : BasePresenter<ListCommentsView> {

    fun viewReady(patientId: String)

    fun listAllCommentsByPatient(patientId: String)

    fun onDeleteButtonClicked(commentEntity: CommentEntity)
}