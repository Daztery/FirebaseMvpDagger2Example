package com.upc.monitoringwalkers.ui.doctor.listPatientsWithoutTherapist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.listPatientsPresenter
import com.upc.monitoringwalkers.listPatientsWithoutTherapistPresenter
import com.upc.monitoringwalkers.model.MWCurrentUser
import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.model.getCurrentUserPreferenceObjectJson
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.doctor.listPatientsWithoutTherapist.view.ListPatientsWithoutTherapistView
import kotlinx.android.synthetic.main.activity_list_choose_patient_without_therapist.*
import kotlinx.android.synthetic.main.content_list_choose_patients_without_therapist.*

class ListPatientsWithoutTherapistActivity : BaseActivity(), ListPatientsWithoutTherapistView {

    private lateinit var therapistId: String
    private val presenter by lazy { listPatientsWithoutTherapistPresenter() }
    private val adapter by lazy { PatientWithoutTherapistAdapter(presenter::onTherapistIdChanged,therapistId) }
    private lateinit var currentUser: MWCurrentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_choose_patient_without_therapist)
        setSupportActionBar(toolbar)
        presenter.setView(this)
        therapistId = intent.extras!!.getString("therapistId").toString()
        initUi()
    }

    private fun initUi() {
        currentUser = getCurrentUserPreferenceObjectJson(this)
        patientWithoutTherapist_list_recycler_view.layoutManager = LinearLayoutManager(this)
        patientWithoutTherapist_list_recycler_view.setHasFixedSize(true)
        patientWithoutTherapist_list_recycler_view.adapter = adapter
        presenter.viewReady(currentUser.id)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun showNoDataDescription() {
        patientWithoutTherapistNoItems.visibility = View.VISIBLE
    }

    override fun hideNoDataDescription() {
        patientWithoutTherapistNoItems.visibility = View.GONE
    }

    override fun addPatientWithoutTherapistToList(patientEntity: PatientEntity) {
        adapter.addPatient(patientEntity)
        patientWithoutTherapistNoItems.visibility = if (adapter.itemCount != 0) View.INVISIBLE else View.VISIBLE
    }

    override fun deletePatientWithoutTherapistFromList(patientEntity: PatientEntity) {
        adapter.removePatient(patientEntity)
    }

}
