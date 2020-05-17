package com.upc.monitoringwalkers.ui.patients.profile.seeDetail

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
import com.upc.monitoringwalkers.ui.patients.profile.seeDetail.view.PatientProfileView
import com.upc.monitoringwalkers.ui.patients.profile.seeGraphics.GraphicPatientDetatilActivity
import kotlinx.android.synthetic.main.activity_patient_profile.*

class PatientProfileActivity : BaseActivity(),
    PatientProfileView {

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

        when(patientEntity.affectation.toString()){
            "HIGH"->{
                patient_profile_affectation.text="Alto"
            }
            "MIDDLE"->{
                patient_profile_affectation.text="Medio"
            }
            "LOW"->{
                patient_profile_affectation.text="Bajo"
            }
        }



        Glide
            .with(this)
            .load("https://cdn2.iconfinder.com/data/icons/covid-19-filled/64/virus-18-512.png")
            .centerCrop()
            .placeholder(R.drawable.ic_person_outline_black_24dp)
            .into(patient_profile_image)
        patient_profile_button_see_graphics.setOnClickListener {
            val intent = Intent(this, GraphicPatientDetatilActivity::class.java)
            intent.putExtra("patientId", currentUser.id)
            this.startActivity(intent)
        }
    }

    override fun onFetchPatientProfileFail(error: String) {
        patient_profile_progress.visibility = View.GONE
        patient_profile_error.apply {
            visibility = View.VISIBLE
            text = error
        }
    }
}
