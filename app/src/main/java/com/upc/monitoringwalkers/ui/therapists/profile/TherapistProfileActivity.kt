package com.upc.monitoringwalkers.ui.therapists.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.model.MWCurrentUser
import com.upc.monitoringwalkers.model.TherapistEntity
import com.upc.monitoringwalkers.model.getCurrentUserPreferenceObjectJson
import com.upc.monitoringwalkers.model.setCurrentUserPreferenceObject
import com.upc.monitoringwalkers.therapistProfilePresenter
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.login.LoginActivity
import com.upc.monitoringwalkers.ui.therapists.profile.view.TherapistProfileView
import kotlinx.android.synthetic.main.activity_therapist_profile.*

class TherapistProfileActivity : BaseActivity(), TherapistProfileView  {

    private val presenter by lazy { therapistProfilePresenter() }
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
        presenter.fetchTherapistProfile(currentUser.id)
        therapist_profile_logout_btn.setOnClickListener {
            presenter.logout()
        }

        therapist_profile_logout.setOnClickListener {
            presenter.logout()
        }

    }

    override fun onFetchTherapistProfileSuccess(therapistEntity: TherapistEntity) {
        therapist_profile_progress.visibility = View.GONE
        therapist_profile.visibility = View.VISIBLE
        therapist_profile_appBar.visibility=View.VISIBLE
        therapist_profile_full_name.text = "${therapistEntity.name} ${therapistEntity.lastName}"
        therapist_profile_email.text = therapistEntity.email
        Glide
            .with(this)
            .load("https://cdn3.iconfinder.com/data/icons/healthcare-medical-lilac-series-vol-1/256/DOCTOR-512.png") // TODO : custom photo
            .centerCrop()
            .placeholder(R.drawable.ic_person_outline_black_24dp)
            .into(therapist_profile_image)
    }

    override fun onFetchTherapistProfileFail(error: String) {
        therapist_profile_progress.visibility = View.GONE
        therapist_profile_error.apply {
            visibility = View.VISIBLE
            text = error
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
