package com.upc.monitoringwalkers.ui.doctor.detailPatient

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.detailPatientinTherapistPresenter
import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.doctor.addComment.AddCommentActivity
import com.upc.monitoringwalkers.ui.doctor.detailPatient.view.DetailPatientView
import com.upc.monitoringwalkers.ui.doctor.listComments.ListCommentsActivity
import kotlinx.android.synthetic.main.activity_detail_patient.*

class DetailPatientActivity : BaseActivity(), DetailPatientView {

    private val presenter by lazy { detailPatientinTherapistPresenter() }
    private lateinit var patientId: String
    private lateinit var therapistId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_patient)
        presenter.setView(this)
        patientId = intent.extras!!.getString("patientId").toString()
        therapistId = intent.extras!!.getString("therapistId").toString()
        initUi()
    }

    private fun initUi() {
        presenter.fetchPatientProfile(patientId)
        supportActionBar!!.setTitle(R.string.detail_patient)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        detail_patient_see_comments.setOnClickListener {
            val intent = Intent(this, ListCommentsActivity::class.java)
            intent.putExtra("therapistId", therapistId)
            intent.putExtra("patientId", patientId)
            this.startActivity(intent)
        }
    }

    override fun onFetchPatientProfileSuccess(patientEntity: PatientEntity) {
        detail_patient_progress.visibility = View.GONE
        detail_patient.visibility = View.VISIBLE
        detail_patient_full_name.text = "${patientEntity.name} ${patientEntity.lastName}"
        detail_patient_email.text = patientEntity.email
        Glide
            .with(this)
            .load(getString(R.string.img_patient))
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
