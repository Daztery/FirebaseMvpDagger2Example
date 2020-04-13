package com.upc.monitoringwalkers.ui.therapists.profile.listPatients

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.model.*
import com.upc.monitoringwalkers.therapistProfilePresenter
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.login.LoginActivity
import com.upc.monitoringwalkers.ui.therapists.profile.listPatients.view.TherapistProfileView
import kotlinx.android.synthetic.main.activity_therapist_profile.*
import kotlinx.android.synthetic.main.content_list_patients_on_therapist_profile.*

class TherapistProfileActivity : BaseActivity(),
    TherapistProfileView {

    private lateinit var therapistId: String
    private val presenter by lazy { therapistProfilePresenter() }
    private val adapter by lazy {
        TherapistProfileAdapter(
            therapistId
        )
    }
    private lateinit var currentUser: MWCurrentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_therapist_profile)
        setSupportActionBar(therapist_profile_toolbar)
        presenter.setView(this)
        initUi()
    }

    private fun initUi() {

        currentUser = getCurrentUserPreferenceObjectJson(this)
        presenter.viewReady(currentUser.id)
        therapistId=currentUser.id
        patient_list_on_therapist_profile_recycler_view.layoutManager = LinearLayoutManager(this)
        patient_list_on_therapist_profile_recycler_view.setHasFixedSize(true)
        patient_list_on_therapist_profile_recycler_view.adapter = adapter
        therapist_profile_logout.setOnClickListener {
            presenter.logout()
        }

    }
    override fun onFetchTherapistProfileSuccess(therapistEntity: TherapistEntity) {
        therapist_profile_appBar.visibility=View.VISIBLE
        therapistId=therapistEntity.id
    }

    override fun onFetchTherapistProfileFail(error: String) {
        patientByTherapistOnProfileNoItems.visibility=View.VISIBLE
    }

    override fun addPatientToTherapist(patientEntity: PatientEntity) {
        adapter.addPatient(patientEntity)
        patientByTherapistOnProfileNoItems.visibility = if (adapter.itemCount != 0) View.INVISIBLE else View.VISIBLE
    }

    override fun logoutError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun logoutSuccess() {
        setCurrentUserPreferenceObject(this, MWCurrentUser())
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
