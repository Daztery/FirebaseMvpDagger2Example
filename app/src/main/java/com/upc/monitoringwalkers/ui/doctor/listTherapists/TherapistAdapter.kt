package com.upc.monitoringwalkers.ui.doctor.listTherapists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.common.showDeleteAdvertice
import com.upc.monitoringwalkers.model.TherapistEntity
import kotlinx.android.synthetic.main.item_therapist.view.*

class TherapistAdapter(private val onDeleteClickHandler: (TherapistEntity) -> Unit) :
    RecyclerView.Adapter<TherapistHolder>() {

    private val items = mutableListOf<TherapistEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TherapistHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_therapist, parent, false)
        return TherapistHolder(view, onDeleteClickHandler)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TherapistHolder, position: Int) {
        val therapist = items[position]
        holder.displayData(therapist)
    }

    fun addTherapist(therapist: TherapistEntity) {
        items.add(therapist)
        notifyItemInserted(items.size - 1)
    }

    fun removeTherapist(therapist: TherapistEntity) {
        items.remove(therapist)
        notifyDataSetChanged()
    }

}

class TherapistHolder(
    itemView: View,
    private inline val onDeleteClickHandler: (TherapistEntity) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    @SuppressLint("SetTextI18n")
    fun displayData(therapist: TherapistEntity) = with(itemView) {
        therapistFullName.text = "${therapist.name} ${therapist.lastName}"
        therapistEmail.text = therapist.email
        therapist_delete.setOnClickListener {
            showDeleteAdvertice(context) {
                onDeleteClickHandler(therapist)
            }
        }
    }
}