package com.upc.monitoringwalkers.firebase.database

import com.upc.monitoringwalkers.model.*

interface FirebaseDatabaseInterface {

    /**
     * Create a Doctor instance to save into database.
     *
     * @param doctorEntity Doctor instance to add into the FirebaseDatabase
     */
    fun createDoctor(doctorEntity: DoctorEntity)

    /**
     * Create a Therapist instance to save into database.
     *
     * @param therapistEntity Therapist instance to add into the FirebaseDatabase
     */
    fun createTherapist(therapistEntity: TherapistEntity)

    /**
     * Create a Patient instance to save into database.
     *
     * @param patientEntity Patient instance to add into the FirebaseDatabase
     */
    fun createPatient(patientEntity: PatientEntity)

    /**
     * Create a Comment instance to save into database.
     *
     * @param commentEntity Comment instance to add into the FirebaseDatabase
     */
    fun createComment(commentEntity: CommentEntity)

    /**
     * Get all the doctor data from the database and parse into an DoctorEntity object.
     *
     * @param id Doctor identifier in string
     * @param onResult Callback that is call when the method is finished
     */
    fun getDoctorProfile(id: String, onResult: (DoctorEntity) -> Unit)

    /**
     * Get all the patient data from the database and parse into an PatientEntity object.
     *
     * @param id Patient identifier in string
     * @param onResult Callback that is call when the method is finished
     */
    fun getPatientProfile(id: String, onResult: (PatientEntity) -> Unit)

    /**
     * Get all the therapist data from the database and parse into an TherapistEntity object.
     *
     * @param id Therapist identifier in string
     * @param onResult Callback that is call when the method is finished
     */
    fun getTherapistProfile(id: String, onResult: (TherapistEntity) -> Unit)

    /**
     * Get the user from FirebaseDatabase type given the identifier
     *
     * @param id User unique identifier to search it from the database
     * @param onResult Callback that is call when the method is finished with the user type as string
     */
    fun getUserType(id: String, onResult: (String) -> Unit)

    /**
     * Get all the USERS where the TYPE is DOCTOR.
     *
     * @param onResult Callback that is call when the result is given from the FirebaseDatabase
     */
    fun getAllDoctors(onResult: (List<DoctorEntity>) -> Unit)

    /**
     * Get all the POINTS where the TYPE is FORCE
     *
     * @param onResult Callback that is call when the result is given from the FirebaseDatabase
     */
    fun getAllPointsForce(patientId: String, onResult: (ArrayList<PointEntity>) -> Unit)

    /**
     * Get the list of therapists given the doctor identifier.
     *
     * @param doctorId Doctor unique identifier
     * @param onResult Callback that is call when the result is given as a list of TherapistEntity
     */
    fun getTherapistByDoctor(doctorId: String, onResult: (List<TherapistEntity>) -> Unit)

    /**
     * Get the list of patients given the doctor identifier.
     *
     * @param doctorId Doctor unique identifier
     * @param onResult Callback that is call when the result is given as a list of PatientEntity
     */
    fun getPatientsByDoctor(doctorId: String, onResult: (List<PatientEntity>) -> Unit)


    /**
     * This method listen all the occurrences to the child of USERS that type is DOCTOR.
     *
     * @param onResult callback that refresh the UI with the database status
     */
    fun listenToDoctors(onResult: (DoctorEntity) -> Unit)

    /**
     * List patients by doctor
     *
     * @param doctorId
     * @param onResult
     */
    fun listPatientsByDoctor(doctorId: String, onResult: (PatientEntity) -> Unit)

    /**
     * List patients by Therapist
     *
     * @param patientEntity
     * @param onResult
     */
    fun listPatientsByTherapist(patientEntity: PatientEntity, onResult: (PatientEntity) -> Unit)

    /**
     * List comments by Patient
     *
     * @param patientId
     * @param onResult
     */
    fun listCommentsByPatient(patientId: String, onResult: (CommentEntity) -> Unit)

    /**
     * List therapists by doctor
     *
     * @param doctorId
     * @param onResult
     */
    fun listTherapistsByDoctor(doctorId: String, onResult: (TherapistEntity) -> Unit)

    /**
     * Delete a user by identifier
     *
     * @param userId
     * @param onResult
     */
    fun deleteUser(userId: String, onResult: (Boolean) -> Unit)

    /**
     * Delete the therapist from patient by identifier
     *
     * @param patientEntity
     * @param onResult
     */
    fun deleteTherapistFromPatient(patientEntity: PatientEntity, onResult: (Boolean) -> Unit)

    /**
     * Update a Patient with new Therapist
     *
     * @param patientEntity
     * @param onResult
     */
    fun updatePatientWithTherapist(patientEntity: PatientEntity, onResult: (Boolean) -> Unit)

    /**
     * List points of Force's graphic by Patient
     *
     * @param patientId
     * @param onResult
     */
    fun getOfForceGraphicByPatient(patientId: String, onResult: (PointEntity) -> Unit)

    /**
     * List points of Speed's graphic by Patient
     *
     * @param patientId
     * @param onResult
     */
    fun getOfSpeedGraphicByPatient(patientId: String, onResult: (PointEntity) -> Unit)



}