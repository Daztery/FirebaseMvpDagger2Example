package com.upc.monitoringwalkers.di.component

import com.upc.monitoringwalkers.di.module.PresentationModule
import com.upc.monitoringwalkers.ui.admin.addDoctor.presenter.AddDoctorPresenter
import com.upc.monitoringwalkers.ui.admin.detailDoctor.presenter.DetailDoctorPresenter
import com.upc.monitoringwalkers.ui.admin.listDoctors.presenter.ListDoctorsPresenter
import com.upc.monitoringwalkers.ui.therapists.profile.addComment.presenter.AddCommentPresenter
import com.upc.monitoringwalkers.ui.doctor.addPacient.presenter.AddPatientPresenter
import com.upc.monitoringwalkers.ui.doctor.addTherapist.presenter.AddTherapistPresenter
import com.upc.monitoringwalkers.ui.doctor.choosePatientOrTherapist.presenter.ChoosePatientOrTherapistPresenter
import com.upc.monitoringwalkers.ui.therapists.profile.detailPatient.presenter.DetailPatientInTherapistPresenter
import com.upc.monitoringwalkers.ui.doctor.detailTherapist.presenter.DetailTherapistPresenter
import com.upc.monitoringwalkers.ui.therapists.profile.listComments.presenter.ListCommentsPresenter
import com.upc.monitoringwalkers.ui.doctor.listPatients.presenter.ListPatientsPresenter
import com.upc.monitoringwalkers.ui.doctor.listPatientsByTherapist.presenter.ListPatientsByTherapistPresenter
import com.upc.monitoringwalkers.ui.doctor.listPatientsWithoutTherapist.presenter.ListPatientsWithoutTherapistPresenter
import com.upc.monitoringwalkers.ui.doctor.listTherapists.presenter.ListTherapistsPresenter
import com.upc.monitoringwalkers.ui.login.presenter.LoginPresenter
import com.upc.monitoringwalkers.ui.patients.profile.seeDetail.presenter.PatientProfilePresenter
import com.upc.monitoringwalkers.ui.patients.profile.seeGraphics.presenter.GraphicPatientDetatilPresenter
import com.upc.monitoringwalkers.ui.resetPassword.presenter.ResetPasswordPresenter
import com.upc.monitoringwalkers.ui.splash.presenter.SplashPresenter
import com.upc.monitoringwalkers.ui.therapists.profile.listPatients.presenter.TherapistProfilePresenter
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

    fun addCommentPresenter(): AddCommentPresenter

    fun listDoctorsPresenter(): ListDoctorsPresenter

    fun therapistProfilePresenter(): TherapistProfilePresenter

    fun listTherapistPresenter(): ListTherapistsPresenter

    fun patientProfilePresenter(): PatientProfilePresenter

    fun listPatientsPresenter(): ListPatientsPresenter

    fun resetPasswordPresenter(): ResetPasswordPresenter

    fun detailDoctorPresenter(): DetailDoctorPresenter

    fun detailTherapistPresenter(): DetailTherapistPresenter

    fun detailPatientInTherapistPresenter(): DetailPatientInTherapistPresenter

    fun choosePatientOrTherapistPresenter(): ChoosePatientOrTherapistPresenter

    fun listPatientsByTherapistPresenter(): ListPatientsByTherapistPresenter

    fun listCommentsByPatientPresenter(): ListCommentsPresenter

    fun listPatientsWithoutTherapistPresenter(): ListPatientsWithoutTherapistPresenter

    fun graphicPatientDetatilPresenter(): GraphicPatientDetatilPresenter

}