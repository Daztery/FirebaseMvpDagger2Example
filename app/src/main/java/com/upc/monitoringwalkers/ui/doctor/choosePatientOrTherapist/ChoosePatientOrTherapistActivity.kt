package com.upc.monitoringwalkers.ui.doctor.choosePatientOrTherapist

import android.content.Intent
import android.os.Bundle
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.choosePatientOrTherapistPresenter
import com.upc.monitoringwalkers.model.MWCurrentUser
import com.upc.monitoringwalkers.model.getCurrentUserPreferenceObjectJson
import com.upc.monitoringwalkers.model.setCurrentUserPreferenceObject
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.doctor.choosePatientOrTherapist.view.ChoosePatientOrTherapistView
import com.upc.monitoringwalkers.ui.doctor.listPatients.ListPatientsActivity
import com.upc.monitoringwalkers.ui.doctor.listTherapists.ListTherapistsActivity
import com.upc.monitoringwalkers.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_choose_patient_therapist_doctor.*

class ChoosePatientOrTherapistActivity : BaseActivity(), ChoosePatientOrTherapistView {

    private val presenter by lazy { choosePatientOrTherapistPresenter() }
    private lateinit var currentUser: MWCurrentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_patient_therapist_doctor)
        setSupportActionBar(toolbar)
        presenter.setView(this)
        initUi()
    }

    private fun initUi() {
        currentUser = getCurrentUserPreferenceObjectJson(this)
        presenter.viewReady(currentUser.id)

        containerPatients.setOnClickListener {
            startActivity(Intent(this, ListPatientsActivity::class.java))
        }

        containerTherapist.setOnClickListener {
            startActivity(Intent(this, ListTherapistsActivity::class.java))
        }

        list_doctor_logout_btn.setOnClickListener {
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


}
