package com.upc.monitoringwalkers.ui.patients.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.model.MWCurrentUser
import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.model.getCurrentUserPreferenceObjectJson
import com.upc.monitoringwalkers.model.setCurrentUserPreferenceObject
import com.upc.monitoringwalkers.patientProfilePresenter
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.login.LoginActivity
import com.upc.monitoringwalkers.ui.patients.profile.view.PatientProfileView
import kotlinx.android.synthetic.main.activity_patient_profile.*

class PatientProfileActivity : BaseActivity(), PatientProfileView {

    private val presenter by lazy { patientProfilePresenter() }
    private lateinit var currentUser: MWCurrentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_profile)
        setSupportActionBar(patient_profile_toolbar)
        presenter.setView(this)
        initUi()
    }

    private fun initUi() {

        currentUser = getCurrentUserPreferenceObjectJson(this)
        presenter.viewReady(currentUser.id)
        presenter.fetchPatientProfile(currentUser.id)

        patient_profile_logout_btn.setOnClickListener {
            presenter.logout()
        }

        patient_profile_logout.setOnClickListener {
            presenter.logout()
        }


    }

    override fun logoutError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun logoutSuccess() {
        setCurrentUserPreferenceObject(this, MWCurrentUser())
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onFetchPatientProfileSuccess(patientEntity: PatientEntity) {
        patient_profile_progress.visibility = View.GONE
        patient_profile.visibility = View.VISIBLE
        patient_profile_appBar.visibility=View.VISIBLE
        patient_profile_full_name.text = "${patientEntity.name} ${patientEntity.lastName}"
        patient_profile_email.text = patientEntity.email
        Glide
            .with(this)
            .load("https://cdn3.iconfinder.com/data/icons/healthcare-medical-lilac-series-vol-1/256/DOCTOR-512.png") // TODO : custom photo
            .centerCrop()
            .placeholder(R.drawable.ic_person_outline_black_24dp)
            .into(patient_profile_image)
    }

    override fun onFetchPatientProfileFail(error: String) {
        patient_profile_progress.visibility = View.GONE
        patient_profile_error.apply {
            visibility = View.VISIBLE
            text = error
        }
    }
}
