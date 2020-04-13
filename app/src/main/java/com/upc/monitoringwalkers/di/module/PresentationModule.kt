package com.upc.monitoringwalkers.di.module

import com.upc.monitoringwalkers.ui.admin.addDoctor.presenter.AddDoctorPresenter
import com.upc.monitoringwalkers.ui.admin.addDoctor.presenter.AddDoctorPresenterImpl
import com.upc.monitoringwalkers.ui.admin.detailDoctor.presenter.DetailDoctorPresenter
import com.upc.monitoringwalkers.ui.admin.detailDoctor.presenter.DetailDoctorPresenterImpl
import com.upc.monitoringwalkers.ui.admin.listDoctors.presenter.ListDoctorsPresenter
import com.upc.monitoringwalkers.ui.admin.listDoctors.presenter.ListDoctorsPresenterImpl
import com.upc.monitoringwalkers.ui.doctor.addPacient.presenter.AddPatientPresenter
import com.upc.monitoringwalkers.ui.doctor.addPacient.presenter.AddPatientPresenterImpl
import com.upc.monitoringwalkers.ui.doctor.addTherapist.presenter.AddTherapistPresenter
import com.upc.monitoringwalkers.ui.doctor.addTherapist.presenter.AddTherapistPresenterImpl
import com.upc.monitoringwalkers.ui.doctor.choosePatientOrTherapist.presenter.ChoosePatientOrTherapistPresenter
import com.upc.monitoringwalkers.ui.doctor.choosePatientOrTherapist.presenter.ChoosePatientOrTherapistPresenterImpl
import com.upc.monitoringwalkers.ui.doctor.detailTherapist.presenter.DetailTherapistPresenter
import com.upc.monitoringwalkers.ui.doctor.detailTherapist.presenter.DetailTherapistPresenterImpl
import com.upc.monitoringwalkers.ui.doctor.listPatients.presenter.ListPatientsPresenter
import com.upc.monitoringwalkers.ui.doctor.listPatients.presenter.ListPatientsPresenterImpl
import com.upc.monitoringwalkers.ui.doctor.listPatientsByTherapist.presenter.ListPatientsByTheparistPresenterImpl
import com.upc.monitoringwalkers.ui.doctor.listPatientsByTherapist.presenter.ListPatientsByTherapistPresenter
import com.upc.monitoringwalkers.ui.doctor.listPatientsWithoutTherapist.presenter.ListPatientsWithoutTheparistPresenterImpl
import com.upc.monitoringwalkers.ui.doctor.listPatientsWithoutTherapist.presenter.ListPatientsWithoutTherapistPresenter
import com.upc.monitoringwalkers.ui.doctor.listTherapists.presenter.ListTherapistsPresenter
import com.upc.monitoringwalkers.ui.doctor.listTherapists.presenter.ListTherapistsPresenterImpl
import com.upc.monitoringwalkers.ui.login.presenter.LoginPresenter
import com.upc.monitoringwalkers.ui.login.presenter.LoginPresenterImpl
import com.upc.monitoringwalkers.ui.patients.profile.presenter.PatientProfilePresenter
import com.upc.monitoringwalkers.ui.patients.profile.presenter.PatientProfilePresenterImpl
import com.upc.monitoringwalkers.ui.resetPassword.presenter.ResetPasswordPresenter
import com.upc.monitoringwalkers.ui.resetPassword.presenter.ResetPasswordPresenterImpl
import com.upc.monitoringwalkers.ui.splash.presenter.SplashPresenter
import com.upc.monitoringwalkers.ui.splash.presenter.SplashPresenterImpl
import com.upc.monitoringwalkers.ui.therapists.profile.detailPatient.presenter.DetailPatientPresenter
import com.upc.monitoringwalkers.ui.therapists.profile.detailPatient.presenter.DetailPatientPresenterImpl
import com.upc.monitoringwalkers.ui.therapists.profile.listPatients.presenter.TherapistProfilePresenter
import com.upc.monitoringwalkers.ui.therapists.profile.listPatients.presenter.TherapistProfilePresenterImpl
import dagger.Binds
import dagger.Module

@Module(includes = [InteractionModule::class])
abstract class PresentationModule {
    @Binds
    abstract fun loginPresenter(loginPresenter: LoginPresenterImpl): LoginPresenter

    @Binds
    abstract fun registerPresenter(registerPresenter: AddPatientPresenterImpl): AddPatientPresenter

    @Binds
    abstract fun splashPresenter(splashPresenter: SplashPresenterImpl): SplashPresenter

    @Binds
    abstract fun addDoctorPresenter(addDoctorPresenter: AddDoctorPresenterImpl): AddDoctorPresenter

    @Binds
    abstract fun listDoctorsPresenter(listDoctorsPresenter: ListDoctorsPresenterImpl): ListDoctorsPresenter

    @Binds
    abstract fun addTherapistPresenter(addTherapistPresenter: AddTherapistPresenterImpl): AddTherapistPresenter

    @Binds
    abstract fun listTherapistPresenter(listTherapistPresenter: ListTherapistsPresenterImpl): ListTherapistsPresenter

    @Binds
    abstract fun detailTherapistPresenter(detailTherapistPresenter: DetailTherapistPresenterImpl): DetailTherapistPresenter

    @Binds
    abstract fun choosePatientOrTherapistPresenter(choosePatientOrTherapistPresenter: ChoosePatientOrTherapistPresenterImpl): ChoosePatientOrTherapistPresenter

    @Binds
    abstract fun patientProfilePresenter(patientProfilePresenter: PatientProfilePresenterImpl): PatientProfilePresenter

    @Binds
    abstract fun listPatientsPresenter(listPatientsPresenter: ListPatientsPresenterImpl): ListPatientsPresenter

    @Binds
    abstract fun resetPasswordPresenter(resetPasswordPresenter: ResetPasswordPresenterImpl): ResetPasswordPresenter

    @Binds
    abstract fun detailDoctorPresenter(detailDoctorPresenter: DetailDoctorPresenterImpl): DetailDoctorPresenter

    @Binds
    abstract fun detailPatientPresenter(detailPatientPresenter: DetailPatientPresenterImpl): DetailPatientPresenter

    @Binds
    abstract fun therapistProfilePresenter(therapistProfilePresenter: TherapistProfilePresenterImpl): TherapistProfilePresenter

    @Binds
    abstract fun listPatientsByTherapistPresenter(listPatientsByTheparistPresenter: ListPatientsByTheparistPresenterImpl): ListPatientsByTherapistPresenter

    @Binds
    abstract fun listPatientsWithoutTherapistPresenter(listPatientsWithoutTheparistPresenter: ListPatientsWithoutTheparistPresenterImpl): ListPatientsWithoutTherapistPresenter
}