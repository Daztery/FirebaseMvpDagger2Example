package com.upc.monitoringwalkers.ui.doctor.listPatientsWithoutTherapist

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.common.shortToast
import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.ui.doctor.detailTherapist.DetailTherapistActivity
import kotlinx.android.synthetic.main.item_patient_without_therapist.view.*

class PatientWithoutTherapistAdapter(private val onTherapistIdChanged: (PatientEntity) -> Unit,
                                     private val therapistId:String) :
    RecyclerView.Adapter<PatientHolder>()  {

    private val items = mutableListOf<PatientEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_patient_without_therapist, parent, false)
        return PatientHolder(view, onTherapistIdChanged,therapistId)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PatientHolder, position: Int) {
        val patient = items[position]
        holder.displayData(patient)
    }

    fun addPatient(patient: PatientEntity) {
        if(patient.therapistId==""){
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
    private inline val onTherapistIdChanged: (PatientEntity) -> Unit,
    private inline val therapistId:String
) : RecyclerView.ViewHolder(itemView) {
    @SuppressLint("SetTextI18n")
    fun displayData(patient: PatientEntity) = with(itemView) {
        patientWithoutTherapistFullName.text = "${patient.name} ${patient.lastName}"
        patientWithoutTherapistEmail.text = patient.email
        containerPatientWithoutTherapist.setOnClickListener {
            patient.therapistId=therapistId
            onTherapistIdChanged(patient)
        }
    }
}