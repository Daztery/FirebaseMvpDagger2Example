package com.upc.monitoringwalkers.ui.doctor.listPatientsByTherapist

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.common.shortToast
import com.upc.monitoringwalkers.common.showDeleteAdvertice
import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.ui.doctor.detailPatient.DetailPatientActivity
import com.upc.monitoringwalkers.ui.doctor.detailTherapist.DetailTherapistActivity
import kotlinx.android.synthetic.main.item_patient_by_therapist.view.*

class PatientByTherapistAdapter(private val onDeleteClickHandler: (PatientEntity) -> Unit,
                                private val therapistId:String) :
    RecyclerView.Adapter<PatientHolder>()  {

    private val items = mutableListOf<PatientEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_patient_by_therapist, parent, false)
        return PatientHolder(view, onDeleteClickHandler,therapistId)
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
    private inline val onDeleteClickHandler: (PatientEntity) -> Unit,
    private inline val therapistId:String
) : RecyclerView.ViewHolder(itemView) {
    @SuppressLint("SetTextI18n")
    fun displayData(patient: PatientEntity) = with(itemView) {
        patientByTherapistFullName.text = "${patient.name} ${patient.lastName}"
        patientByTherapistEmail.text = patient.email
        containerPatientByTherapist.setOnClickListener{
            //shortToast(context,patient.id)
            val intent = Intent(context, DetailPatientActivity::class.java)
            intent.putExtra("patientId", patient.id)
            intent.putExtra("therapistId",therapistId)
            context.startActivity(intent)
        }
        patientByTherapist_delete.setOnClickListener {
            showDeleteAdvertice(context) {
                patient.therapistId=""
                onDeleteClickHandler(patient)
            }
        }
        Glide
            .with(this)
            .load("https://cdn2.iconfinder.com/data/icons/covid-19-filled/64/virus-18-512.png")
            .centerCrop()
            .placeholder(R.drawable.ic_person_outline_black_24dp)
            .into(img_user)
    }
}