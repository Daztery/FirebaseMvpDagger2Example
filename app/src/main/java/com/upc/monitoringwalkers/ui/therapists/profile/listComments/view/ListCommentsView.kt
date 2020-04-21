package com.upc.monitoringwalkers.ui.therapists.profile.listComments.view

import com.upc.monitoringwalkers.model.CommentEntity

interface ListCommentsView {

    fun showNoDataDescription()

    fun hideNoDataDescription()

    fun addCommentToList(commentEntity: CommentEntity)

    fun deleteCommentFromList(commentEntity: CommentEntity)

}