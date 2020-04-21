package com.upc.monitoringwalkers.ui.therapists.profile.listComments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.listCommentsByPatientPresenter
import com.upc.monitoringwalkers.model.CommentEntity
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.therapists.profile.addComment.AddCommentActivity
import com.upc.monitoringwalkers.ui.therapists.profile.listComments.view.ListCommentsView
import kotlinx.android.synthetic.main.activity_list_comments.*
import kotlinx.android.synthetic.main.content_list_comments.*

class ListCommentsActivity : BaseActivity(), ListCommentsView {

    private val presenter by lazy { listCommentsByPatientPresenter() }
    private val adapter by lazy { CommentAdapter(presenter::onDeleteButtonClicked) }
    private lateinit var patientId: String
    private lateinit var therapistId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_comments)
        setSupportActionBar(toolbar)
        presenter.setView(this)
        therapistId = intent.extras!!.getString("therapistId").toString()
        patientId = intent.extras!!.getString("patientId").toString()
        initUi()
    }

    private fun initUi() {
        comment_list_recycler_view.layoutManager = LinearLayoutManager(this)
        comment_list_recycler_view.setHasFixedSize(true)
        comment_list_recycler_view.adapter = adapter
        presenter.viewReady(patientId)
        fab.setOnClickListener {
            val intent = Intent(this, AddCommentActivity::class.java)
            intent.putExtra("therapistId", therapistId)
            intent.putExtra("patientId", patientId  )
            this.startActivity(intent)
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun showNoDataDescription() {
        commentNoItems.visibility = View.VISIBLE
    }

    override fun hideNoDataDescription() {
        commentNoItems.visibility = View.GONE
    }

    override fun addCommentToList(commentEntity: CommentEntity) {
        adapter.addCommentToList(commentEntity)
        commentNoItems.visibility = if (adapter.itemCount != 0) View.INVISIBLE else View.VISIBLE
    }

    override fun deleteCommentFromList(commentEntity: CommentEntity) {
        adapter.removeComment(commentEntity)
    }


}
