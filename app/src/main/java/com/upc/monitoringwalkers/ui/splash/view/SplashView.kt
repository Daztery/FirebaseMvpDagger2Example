package com.upc.monitoringwalkers.ui.splash.view

interface SplashView {

    fun onCurrentUserIsDoctor()

    fun onCurrentUserIsPatient()

    fun onCurrentUserIsTherapist()

    fun onCurrentUserIsAdmin()

    fun onCurrentUserIsEmpty()

}