package com.upc.monitoringwalkers.ui.doctor.listComments.view

import com.upc.monitoringwalkers.model.CommentEntity
import com.upc.monitoringwalkers.model.PatientEntity

interface ListCommentsView {

    fun showNoDataDescription()

    fun hideNoDataDescription()

    fun addCommentToList(commentEntity: CommentEntity)

    fun deleteCommentFromList(commentEntity: CommentEntity)

}