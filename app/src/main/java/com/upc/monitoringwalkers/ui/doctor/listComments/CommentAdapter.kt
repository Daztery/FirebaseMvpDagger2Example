package com.upc.monitoringwalkers.ui.doctor.listComments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.model.CommentEntity
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter(private val onDeleteClickHandler: (CommentEntity) -> Unit) :
    RecyclerView.Adapter<CommentHolder>() {

    private val items = mutableListOf<CommentEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentHolder(view, onDeleteClickHandler)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        val comment = items[position]
        holder.displayData(comment)
    }

    fun addCommentToList(comment: CommentEntity) {
        items.add(comment)
        notifyItemInserted(items.size - 1)
    }

    fun removeComment(comment: CommentEntity) {
        items.remove(comment)
        notifyDataSetChanged()
    }

}

class CommentHolder(
    itemView: View,
    private inline val onDeleteClickHandler: (CommentEntity) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    @SuppressLint("SetTextI18n")
    fun displayData(comment: CommentEntity) = with(itemView) {
        commentDate.text = comment.date
        commentText.text = comment.comment
        /*patient_delete.setOnClickListener {
            showDeleteAdvertice(context) {
                onDeleteClickHandler(patient)
            }
        }*/
    }
}