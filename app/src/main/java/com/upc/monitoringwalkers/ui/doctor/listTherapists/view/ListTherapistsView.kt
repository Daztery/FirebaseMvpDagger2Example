package com.upc.monitoringwalkers.ui.doctor.listTherapists.view

import com.upc.monitoringwalkers.model.TherapistEntity

interface ListTherapistsView {

    fun showNoDataDescription()

    fun hideNoDataDescription()

    fun addTherapist(therapistEntity: TherapistEntity)

    fun deleteTherapist(therapistEntity: TherapistEntity)

}