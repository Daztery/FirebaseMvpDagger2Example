package com.upc.monitoringwalkers.ui.doctor.listTherapists

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.common.shortToast
import com.upc.monitoringwalkers.common.showDeleteAdvertice
import com.upc.monitoringwalkers.model.TherapistEntity
import com.upc.monitoringwalkers.ui.doctor.detailTherapist.DetailTherapistActivity
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
    fun displayData(therapistEntity: TherapistEntity) = with(itemView) {
        therapistFullName.text = "${therapistEntity.name} ${therapistEntity.lastName}"
        therapistEmail.text = therapistEntity.email
        therapist_delete.setOnClickListener {
            showDeleteAdvertice(context) {
                onDeleteClickHandler(therapistEntity)
                shortToast(context, "Terapeuta eliminado")
            }
        }
        container_item_therapist.setOnClickListener {
            val intent = Intent(context, DetailTherapistActivity::class.java)
            intent.putExtra("therapistId", therapistEntity.id)
            context.startActivity(intent)
        }
        Glide
            .with(this)
            .load("https://cdn3.iconfinder.com/data/icons/healthcare-medical-lilac-series-vol-1/256/DOCTOR-512.png")
            .centerCrop()
            .placeholder(R.drawable.ic_person_outline_black_24dp)
            .into(img_user_therapist)

    }
}