package com.upc.monitoringwalkers.model

import android.os.Bundle

data class CommentEntity(
    var id: String = "",
    var therapistId: String = "",
    var comment: String = "",
    val date: String = ""
){
    companion object {
        fun from(bundle: Bundle): TherapistEntity {
            return TherapistEntity(
            )
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        with(bundle) {
            putString("id", id)
        }
        return bundle
    }
}

fun CommentEntity.mapToComment() = TherapistEntity(id, therapistId, comment, date)
fun CommentEntity.isValid() = therapistId.isNotBlank()
        && comment.isNotBlank()
        && date.isNotBlank()