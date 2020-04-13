package com.upc.monitoringwalkers.ui.therapists.profile.detailPatient

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.detailPatientPresenter
import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.therapists.profile.detailPatient.view.DetailPatientView
import kotlinx.android.synthetic.main.activity_detail_patient_on_profile.*

class DetailPatientActivity : BaseActivity(), DetailPatientView {

    private val presenter by lazy { detailPatientPresenter() }
    private lateinit var patientId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_patient_on_profile)
        presenter.setView(this)
        patientId = intent.extras!!.getString("patientId").toString()
        initUi()
    }

    private fun initUi() {
        presenter.fetchPatientProfile(patientId)
        supportActionBar!!.setTitle(R.string.detail_therapist)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onFetchPatientProfileSuccess(patientEntity: PatientEntity) {
        detail_patient_progress.visibility = View.GONE
        detail_patient.visibility = View.VISIBLE
        detail_patient_full_name.text = "${patientEntity.name} ${patientEntity.lastName}"
        detail_patient_email.text = patientEntity.email
        Glide
            .with(this)
            .load("https://cdn2.iconfinder.com/data/icons/covid-19-filled/64/virus-18-512.png")
            .centerCrop()
            .placeholder(R.drawable.ic_person_outline_black_24dp)
            .into(detail_patient_image)
    }

    override fun onFetchPatientProfileFail(error: String) {
        detail_patient_progress.visibility = View.GONE
        detail_patient_error.apply {
            visibility = View.VISIBLE
            text = error
        }
    }

}
