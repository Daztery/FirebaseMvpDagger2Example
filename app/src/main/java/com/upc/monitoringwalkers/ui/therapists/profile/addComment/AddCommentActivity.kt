package com.upc.monitoringwalkers.ui.therapists.profile.addComment

import android.os.Bundle
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.addCommentPresenter
import com.upc.monitoringwalkers.common.onTextChanged
import com.upc.monitoringwalkers.common.shortToast
import com.upc.monitoringwalkers.common.showGeneralError
import com.upc.monitoringwalkers.common.showRegisterError
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.therapists.profile.addComment.view.AddCommentView
import kotlinx.android.synthetic.main.activity_add_comment.*
import java.text.SimpleDateFormat
import java.util.*

class AddCommentActivity : BaseActivity(), AddCommentView {

    private val presenter by lazy { addCommentPresenter() }
    private lateinit var therapistId: String
    private lateinit var patientId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_comment)
        presenter.setView(this)
        therapistId = intent.extras!!.getString("therapistId").toString()
        patientId = intent.extras!!.getString("patientId").toString()
        initUi()
    }


    private fun initUi() {
        val date = getCurrentDateTime()

        register_comment_edit.onTextChanged {
            presenter.onTextChanged(it!!)
            presenter.onDateChanged(date.toString())
            presenter.onPatientChanged(patientId)
            presenter.onTherapistIdChanged(therapistId)

        }

        material_comment_button_register.setOnClickListener {
            presenter.onRegisterClicked(therapistId)
            finish()
        }

        supportActionBar!!.setTitle("Agregar comentario")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }
    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    override fun onRegisterSuccess() {
        hideLoadingDialog()
        onBackPressed()
        shortToast(this, "Registro comentario exitoso")
    }

    override fun showSignUpError() {
        hideLoadingDialog()
        showRegisterError(this)
    }

}
