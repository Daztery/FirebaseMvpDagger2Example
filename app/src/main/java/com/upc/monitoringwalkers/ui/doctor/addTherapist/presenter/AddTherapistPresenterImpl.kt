package com.upc.monitoringwalkers.ui.doctor.addTherapist.presenter

import com.upc.monitoringwalkers.common.arePasswordsSame
import com.upc.monitoringwalkers.common.isEmailValid
import com.upc.monitoringwalkers.common.isPasswordValid
import com.upc.monitoringwalkers.firebase.authentication.FirebaseAuthenticationInterface
import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.model.RegisterTherapistModel
import com.upc.monitoringwalkers.model.TherapistEntity
import com.upc.monitoringwalkers.ui.doctor.addTherapist.view.AddTherapistView
import javax.inject.Inject

class AddTherapistPresenterImpl @Inject constructor(
    private val databaseInterface: FirebaseDatabaseInterface,
    private val authenticationInterface: FirebaseAuthenticationInterface
) : AddTherapistPresenter {


    private lateinit var view: AddTherapistView

    private val therapistModel = RegisterTherapistModel()

    override fun setView(view: AddTherapistView) {
        this.view = view
    }

    override fun onEmailChanged(email: String) {
        therapistModel.email = email
        if (!isEmailValid(email)) {
            view.showEmailError()
        }
    }

    override fun onPasswordChanged(password: String) {
        therapistModel.password = password
        if (!isPasswordValid(password)) {
            view.showPasswordError()
        }
    }

    override fun onRepeatPasswordChanged(repeatPassword: String) {
        therapistModel.repeatPassword = repeatPassword
        if (!arePasswordsSame(therapistModel.password, repeatPassword)) {
            view.showPasswordMatchingError()
        }
    }

    override fun onNameChanged(name: String) {
        therapistModel.name = name
    }

    override fun onLastNameChanged(lastName: String) {
        therapistModel.lastName = lastName
    }

    override fun onSpecialityChanged(speciality: String) {
        therapistModel.specialty = speciality
    }

    override fun onRegisterClicked(doctorId: String) {
        therapistModel.doctorId = doctorId
        if (therapistModel.isValid()) {
            authenticationInterface.register(
                therapistModel.email,
                therapistModel.password
            ) { isSuccessful ->
                onRegisterResult(isSuccessful, therapistModel)
            }
        } else {
            view.showSignUpError()
        }
    }


    private fun onRegisterResult(isSuccessful: Boolean, therapist: RegisterTherapistModel) {
        if (isSuccessful) {
            createTherapist(therapist)
            view.onRegisterSuccess()
        } else {
            view.showSignUpError()
        }
    }

    private fun createTherapist(therapist: RegisterTherapistModel) {
        val id = authenticationInterface.getUserId()
        val therapistEntity = TherapistEntity(
            id,
            therapist.name,
            therapist.lastName,
            therapist.email,
            therapist.type,
            therapist.specialty,
            therapist.doctorId
        )
        databaseInterface.createTherapist(therapistEntity)
    }

}