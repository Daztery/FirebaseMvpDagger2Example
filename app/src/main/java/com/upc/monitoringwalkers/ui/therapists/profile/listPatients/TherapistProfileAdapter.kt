package com.upc.monitoringwalkers.ui.therapists.profile.listPatients

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.common.shortToast
import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.ui.therapists.profile.detailPatient.DetailPatientActivity
import kotlinx.android.synthetic.main.item_patient_on_therapist_profile.view.*

class TherapistProfileAdapter(private val therapistId:String) :
    RecyclerView.Adapter<PatientHolder>()  {

    private val items = mutableListOf<PatientEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_patient_on_therapist_profile, parent, false)
        return PatientHolder(
            view,
            therapistId
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PatientHolder, position: Int) {
        val patient = items[position]
        holder.displayData(patient)

    }

    fun addPatient(patient: PatientEntity) {
        if(patient.therapistId!="" && patient.therapistId==therapistId){
            items.add(patient)
            notifyItemInserted(items.size - 1)
        }
    }

    fun removePatient(patient: PatientEntity) {
        items.remove(patient)
        notifyDataSetChanged()
    }

}

class PatientHolder(
    itemView: View,
    private inline val therapistId:String
) : RecyclerView.ViewHolder(itemView) {
    @SuppressLint("SetTextI18n")
    fun displayData(patient: PatientEntity) = with(itemView) {
        patientFullName.text = "${patient.name} ${patient.lastName}"
        patientEmail.text = patient.email
        container_item_patient.setOnClickListener {
            //shortToast(context,patient.id)
            val intent = Intent(context, DetailPatientActivity::class.java)
            intent.putExtra("patientId", patient.id)
            context.startActivity(intent)
        }
        Glide
            .with(this)
            .load("https://cdn2.iconfinder.com/data/icons/covid-19-filled/64/virus-18-512.png")
            .centerCrop()
            .placeholder(R.drawable.ic_person_outline_black_24dp)
            .into(img_user)
    }
}