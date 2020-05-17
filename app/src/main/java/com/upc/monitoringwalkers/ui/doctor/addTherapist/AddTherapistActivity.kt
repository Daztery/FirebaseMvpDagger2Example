package com.upc.monitoringwalkers.ui.doctor.addTherapist

import android.os.Bundle
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.common.*
import com.upc.monitoringwalkers.model.getCurrentUserPreferenceObjectJson
import com.upc.monitoringwalkers.registerTherapistPresenter
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.doctor.addTherapist.view.AddTherapistView
import kotlinx.android.synthetic.main.activity_add_therapist.*

class AddTherapistActivity : BaseActivity(), AddTherapistView {

    private var flag:Boolean=false
    private val presenter by lazy { registerTherapistPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_therapist)
        presenter.setView(this)
        initUi()
    }

    private fun initUi() {

        register_therapist_email_edit.onTextChanged {
            presenter.onEmailChanged(it!!)
        }

        register_therapist_speciality_edit.onTextChanged {
            presenter.onSpecialityChanged(it!!)
        }

        register_therapist_password_edit.onTextChanged {
            presenter.onPasswordChanged(it!!)
        }

        register_therapist_confirm_password_edit.onTextChanged {
            presenter.onRepeatPasswordChanged(it!!)
        }

        register_therapist_name_edit.onTextChanged {
            presenter.onNameChanged(it!!)
        }

        register_therapist_last_name_edit.onTextChanged {
            presenter.onLastNameChanged(it!!)
        }

        material_therapist_button_register.setOnClickListener {
            showLoadingDialog()
            val doctor = getCurrentUserPreferenceObjectJson(this)
            presenter.onRegisterClicked(doctor.id)
        }

        supportActionBar!!.setTitle(R.string.add_therapist)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onRegisterSuccess() {
        hideLoadingDialog()
        onBackPressed()
        shortToast(this, "Registro terapeuta exitoso")
    }

    override fun showSignUpError() {
        hideLoadingDialog()

        if (!arePasswordsSame(register_therapist_password_edit.text.toString(),register_therapist_confirm_password_edit.text.toString())) {
            showPasswordSameError(this)
        }else{
            showRegisterError(this)
        }

    }

    override fun showEmailError() {
        register_therapist_email_edit.error = getString(R.string.email_error)
    }

    override fun showPasswordError() {
        register_therapist_password_edit.error = getString(R.string.password_error)

    }

    override fun showPasswordMatchingError() {
        register_therapist_confirm_password_edit.error = getString(R.string.password_error)
    }

}
