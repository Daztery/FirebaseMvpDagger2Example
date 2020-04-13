package com.upc.monitoringwalkers.ui.doctor.listPatientsByTherapist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.listPatientsByTherapistPresenter
import com.upc.monitoringwalkers.model.MWCurrentUser
import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.model.getCurrentUserPreferenceObjectJson
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.doctor.listPatientsByTherapist.view.ListPatientsByTherapistView
import kotlinx.android.synthetic.main.activity_list_choose_patient_by_therapist.*
import kotlinx.android.synthetic.main.content_list_choose_patients_by_therapist.*

class ListPatientsByTherapistActivity : BaseActivity(), ListPatientsByTherapistView {

    private lateinit var therapistId: String
    private val presenter by lazy { listPatientsByTherapistPresenter() }
    private val adapter by lazy { PatientByTherapistAdapter(presenter::onDeleteTherapistIdClicked,therapistId) }
    private lateinit var currentUser: MWCurrentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_choose_patient_by_therapist)
        setSupportActionBar(toolbar)
        presenter.setView(this)
        therapistId = intent.extras!!.getString("therapistId").toString()
        initUi()
    }

    private fun initUi() {
        currentUser = getCurrentUserPreferenceObjectJson(this)
        patientByTherapist_list_recycler_view.layoutManager = LinearLayoutManager(this)
        patientByTherapist_list_recycler_view.setHasFixedSize(true)
        patientByTherapist_list_recycler_view.adapter = adapter
        presenter.viewReady(currentUser.id)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun showNoDataDescription() {
        patientByTherapistNoItems.visibility = View.VISIBLE
    }

    override fun hideNoDataDescription() {
        patientByTherapistNoItems.visibility = View.GONE
    }

    override fun addPatientToTherapist(patientEntity: PatientEntity) {
        adapter.addPatient(patientEntity)
        patientByTherapistNoItems.visibility = if (adapter.itemCount != 0) View.INVISIBLE else View.VISIBLE
    }

    override fun deletePatientWithTherapistFromList(patientEntity: PatientEntity) {
        adapter.removePatient(patientEntity)
    }

}
