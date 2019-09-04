package com.upc.monitoringwalkers.ui.doctor.addPacient

import android.content.Intent
import android.os.Bundle
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.common.onTextChanged
import com.upc.monitoringwalkers.common.shortToast
import com.upc.monitoringwalkers.common.showGeneralError
import com.upc.monitoringwalkers.model.MWCurrentUser
import com.upc.monitoringwalkers.model.getCurrentUserPreferenceObjectJson
import com.upc.monitoringwalkers.model.setCurrentUserPreferenceObject
import com.upc.monitoringwalkers.registerPresenter
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.doctor.addPacient.view.AddPatientView
import com.upc.monitoringwalkers.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_add_patient.*

class AddPatientActivity : BaseActivity(), AddPatientView {

    private val presenter by lazy { registerPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_patient)
        presenter.setView(this)
        initUi()
    }

    private fun initUi() {
        register_email_edit.onTextChanged {
            presenter.onEmailChanged(it!!)
        }
        register_password_edit.onTextChanged {
            presenter.onPasswordChanged(it!!)
        }
        register_confirm_password_edit.onTextChanged {
            presenter.onRepeatPasswordChanged(it!!)
        }

        register_age_edit.onTextChanged {
            presenter.onAgeChanged(it!!)
        }

        register_treatment_edit.onTextChanged {
            presenter.onTreatmentChanged(it!!)
        }

        register_name_edit.onTextChanged {
            presenter.onNameChanged(it!!)
        }

        register_last_name_edit.onTextChanged {
            presenter.onLastNameChanged(it!!)
        }

        material_button_register.setOnClickListener {
            val doctor = getCurrentUserPreferenceObjectJson(this)
            presenter.onRegisterClicked(doctor.id)
        }

        material_button_logout.setOnClickListener {
            presenter.logout()
        }
    }

    override fun onRegisterSuccess() {
        shortToast(this, "Registro paciente exitoso")
    }

    override fun showSignUpError() {
        showGeneralError(this)
    }


    override fun showEmailError() {
        register_email_edit.error = getString(R.string.email_error)
    }

    override fun showPasswordError() {
        register_password_edit.error = getString(R.string.password_error)
    }

    override fun showPasswordMatchingError() {
        register_confirm_password_edit.error = getString(R.string.password_error)
    }

    override fun onLogoutSuccess() {
        setCurrentUserPreferenceObject(this, MWCurrentUser())
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
