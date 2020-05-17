package com.upc.monitoringwalkers.ui.doctor.addPacient

import android.os.Bundle
import com.upc.monitoringwalkers.R
import com.upc.monitoringwalkers.common.*
import com.upc.monitoringwalkers.model.Affectation
import com.upc.monitoringwalkers.model.getCurrentUserPreferenceObjectJson
import com.upc.monitoringwalkers.registerPatientPresenter
import com.upc.monitoringwalkers.ui.base.BaseActivity
import com.upc.monitoringwalkers.ui.doctor.addPacient.view.AddPatientView
import kotlinx.android.synthetic.main.activity_add_patient.*

class AddPatientActivity : BaseActivity(), AddPatientView {

    var flag:Boolean=false
    private val presenter by lazy { registerPatientPresenter() }

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

        register_weight_edit.onTextChanged {
            presenter.onWeightChanged(it!!)
        }

        radio_button_low.setOnClickListener {
            presenter.onAffectionChanged(Affectation.LOW)
        }

        radio_button_middle.setOnClickListener {
            presenter.onAffectionChanged(Affectation.MIDDLE)
        }

        radio_button_high.setOnClickListener {
            presenter.onAffectionChanged(Affectation.HIGH)
        }


        material_patient_button_register.setOnClickListener {
            showLoadingDialog()
            val doctor = getCurrentUserPreferenceObjectJson(this)
            presenter.onRegisterClicked(doctor.id)
        }
        supportActionBar!!.setTitle(R.string.add_patient)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onRegisterSuccess() {
        hideLoadingDialog()
        onBackPressed()
        shortToast(this, "Registro paciente exitoso")
    }

    override fun showSignUpError() {
        hideLoadingDialog()
        if (!arePasswordsSame(register_password_edit.text.toString(),register_confirm_password_edit.text.toString())) {
            showPasswordSameError(this)
        }else{
            showRegisterError(this)
        }
    }

    override fun showEmailError() {
        register_email_edit.error = getString(R.string.email_error)
    }

    override fun showPasswordError() {
        register_password_edit.error = getString(R.string.password_error)
    }

    override fun showPasswordMatchingError() {
        register_confirm_password_edit.error = getString(R.string.password_error)
        flag=true
    }


}
