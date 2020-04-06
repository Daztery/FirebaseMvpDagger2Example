package com.upc.monitoringwalkers.ui.doctor.detailTherapist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.detailTherapistPresenter
import com.upc.monitoringwalkers.model.TherapistEntity
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.doctor.detailTherapist.view.DetailTherapistView
import com.upc.monitoringwalkers.ui.therapists.profile.view.TherapistProfileView
import kotlinx.android.synthetic.main.activity_detail_therapist.*

class DetailTherapistActivity : BaseActivity(),DetailTherapistView {

    private val presenter by lazy { detailTherapistPresenter() }
    private lateinit var therapistId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_therapist)
        presenter.setView(this)
        therapistId = intent.extras!!.getString("therapistId").toString()

        initUi()
    }

    private fun initUi() {
        presenter.fetchTherapistProfile(therapistId)
        supportActionBar!!.setTitle(R.string.detail_therapist)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onFetchTherapistProfileSuccess(therapistEntity: TherapistEntity) {
        detail_therapist_progress.visibility = View.GONE
        detail_therapist_profile.visibility = View.VISIBLE
        detail_therapist_full_name.text = "${therapistEntity.name} ${therapistEntity.lastName}"
        detail_therapist_email.text = therapistEntity.email
        Glide
            .with(this)
            .load("https://cdn3.iconfinder.com/data/icons/healthcare-medical-lilac-series-vol-1/256/DOCTOR-512.png") // TODO : custom photo
            .centerCrop()
            .placeholder(R.drawable.ic_person_outline_black_24dp)
            .into(detail_therapist_image)
    }

    override fun onFetchTherapistProfileFail(error: String) {
        detail_therapist_progress.visibility = View.GONE
        detail_therapist_error.apply {
            visibility = View.VISIBLE
            text = error
        }
    }

}
