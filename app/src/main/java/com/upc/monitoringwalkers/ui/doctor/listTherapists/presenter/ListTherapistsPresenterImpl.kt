package com.upc.monitoringwalkers.ui.doctor.listTherapists.presenter

import android.util.Log
import com.upc.monitoringwalkers.firebase.authentication.FirebaseAuthenticationInterface
import com.upc.monitoringwalkers.firebase.database.FirebaseDatabaseInterface
import com.upc.monitoringwalkers.model.PatientEntity
import com.upc.monitoringwalkers.model.TherapistEntity
import com.upc.monitoringwalkers.ui.doctor.listTherapists.view.ListTherapistsView
import javax.inject.Inject

class ListTherapistsPresenterImpl @Inject constructor(
    private val databaseInterface: FirebaseDatabaseInterface
) : ListTherapistsPresenter {

    private lateinit var view: ListTherapistsView

    override fun setView(view: ListTherapistsView) {
        this.view = view
    }


    override fun viewReady(doctorId: String) {
        listAllTherapistByDoctor(doctorId)
    }

    override fun listAllTherapistByDoctor(doctorId: String) {
        databaseInterface.listTherapistsByDoctor(doctorId) {
            view.addTherapist(it)
        }
    }


    override fun onDeleteButtonClicked(therapistEntity: TherapistEntity) {
        var patients:ArrayList<PatientEntity> = ArrayList()

        databaseInterface.listPatientsByDoctor(therapistEntity.doctorId) {
            patients.add(it)
            if (therapistEntity.id == it.therapistId) {
                Log.i(
                    "DELETE",
                    "${it.name} ${it.email} ${it.lastName} ${it.type}"
                )
                it.therapistId = ""
                databaseInterface.deleteTherapistFromPatient(it) {
                    view.deletePatientsWithTherapistId(therapistEntity)
                }
            }

            databaseInterface.deleteUser(therapistEntity.id) {
                view.deleteTherapist(therapistEntity)
            }
        }

        if(patients.size==0){
            databaseInterface.deleteUser(therapistEntity.id) {
                view.deleteTherapist(therapistEntity)
            }
        }

    }


    override fun deleteAllPatientsHaveTherapistId(doctorId: String) {

        databaseInterface.listPatientsByDoctor(doctorId) {
            print(it)
        }

    }


}