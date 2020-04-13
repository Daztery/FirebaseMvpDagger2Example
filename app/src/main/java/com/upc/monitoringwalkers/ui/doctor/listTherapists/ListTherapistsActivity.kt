package com.upc.monitoringwalkers.ui.doctor.listTherapists

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.listTherapistsPresenter
import com.upc.monitoringwalkers.model.MWCurrentUser
import com.upc.monitoringwalkers.model.TherapistEntity
import com.upc.monitoringwalkers.model.getCurrentUserPreferenceObjectJson
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.doctor.addTherapist.AddTherapistActivity
import com.upc.monitoringwalkers.ui.doctor.listTherapists.view.ListTherapistsView
import kotlinx.android.synthetic.main.activity_list_therapists.*
import kotlinx.android.synthetic.main.content_list_therapists.*

class ListTherapistsActivity : BaseActivity(), ListTherapistsView {

    private val presenter by lazy { listTherapistsPresenter() }
    private val adapter by lazy { TherapistAdapter(presenter::onDeleteButtonClicked) }
    private lateinit var currentUser: MWCurrentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_therapists)
        setSupportActionBar(toolbar)
        presenter.setView(this)
        initUi()
    }

    private fun initUi() {
        currentUser = getCurrentUserPreferenceObjectJson(this)
        therapist_list_recycler_view.layoutManager = LinearLayoutManager(this)
        therapist_list_recycler_view.setHasFixedSize(true)
        therapist_list_recycler_view.adapter = adapter
        presenter.viewReady(currentUser.id)
        fab.setOnClickListener {
            startActivity(Intent(this, AddTherapistActivity::class.java))
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun showNoDataDescription() {
        therapistNoItems.visibility = View.VISIBLE
    }

    override fun hideNoDataDescription() {
        therapistNoItems.visibility = View.GONE
    }

    override fun addTherapist(therapistEntity: TherapistEntity) {
        adapter.addTherapist(therapistEntity)
        therapistNoItems.visibility = if (adapter.itemCount != 0) View.INVISIBLE else View.VISIBLE
    }

    override fun deleteTherapist(therapistEntity: TherapistEntity) {
        adapter.removeTherapist(therapistEntity)
    }

    override fun deletePatientsWithTherapistId(therapistEntity: TherapistEntity){
        print(therapistEntity.id)
    }


}
