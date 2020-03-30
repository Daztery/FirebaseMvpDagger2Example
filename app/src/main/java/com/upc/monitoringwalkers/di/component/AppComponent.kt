package com.upc.monitoringwalkers.di.component

import com.upc.monitoringwalkers.di.module.PresentationModule
import com.upc.monitoringwalkers.ui.admin.addDoctor.presenter.AddDoctorPresenter
import com.upc.monitoringwalkers.ui.admin.detailDoctor.presenter.DetailDoctorPresenter
import com.upc.monitoringwalkers.ui.admin.listDoctors.presenter.ListDoctorsPresenter
import com.upc.monitoringwalkers.ui.doctor.addPacient.presenter.AddPatientPresenter
import com.upc.monitoringwalkers.ui.doctor.addTherapist.presenter.AddTherapistPresenter
import com.upc.monitoringwalkers.ui.doctor.choosePatientOrTherapist.presenter.ChoosePatientOrTherapistPresenter
import com.upc.monitoringwalkers.ui.doctor.listPatients.presenter.ListPatientsPresenter
import com.upc.monitoringwalkers.ui.doctor.listTherapists.presenter.ListTherapistsPresenter
import com.upc.monitoringwalkers.ui.login.presenter.LoginPresenter
import com.upc.monitoringwalkers.ui.patients.profile.presenter.PatientProfilePresenter
import com.upc.monitoringwalkers.ui.resetPassword.presenter.ResetPasswordPresenter
import com.upc.monitoringwalkers.ui.splash.presenter.SplashPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [PresentationModule::class])
@Singleton
interface AppComponent {

    fun loginPresenter(): LoginPresenter

    fun registerPatientPresenter(): AddPatientPresenter

    fun registerTherapistPresenter(): AddTherapistPresenter

    fun splashPresenter(): SplashPresenter

    fun addDoctorPresenter(): AddDoctorPresenter

    fun listDoctorsPresenter(): ListDoctorsPresenter

    fun therapistProfilePresenter(): AddTherapistPresenter //TODO change and create profile

    fun listTherapistPresenter(): ListTherapistsPresenter

    fun patientProfilePresenter(): PatientProfilePresenter

    fun listPatientsPresenter(): ListPatientsPresenter

    fun resetPasswordPresenter(): ResetPasswordPresenter

    fun detailDoctorPresenter(): DetailDoctorPresenter

    fun choosePatientOrTherapistPresenter(): ChoosePatientOrTherapistPresenter

}