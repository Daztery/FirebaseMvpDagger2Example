package com.upc.monitoringwalkers.firebase.database

import android.util.Log
import com.google.firebase.database.*
import com.upc.monitoringwalkers.model.*
import javax.inject.Inject

private const val KEY_USER = "user"
private const val KEY_TYPE_USER = "type"
private const val KEY_THERAPIST = "THERAPIST"
private const val KEY_PATIENT = "PATIENT"
private const val KEY_DOCTOR = "DOCTOR"


class FirebaseDatabaseManager @Inject constructor(private val database: FirebaseDatabase) :
    FirebaseDatabaseInterface {


    override fun updatePatientWithTherapist(patientEntity: PatientEntity, onResult: (Boolean) -> Unit) {
        database.reference.child(KEY_USER).child(patientEntity.id).setValue(patientEntity)
     }

    override fun deleteTherapistInPatient(therapistId: String, onResult: (Boolean) -> Unit) {
        //database.reference.child(KEY_USER).orderByChild("doctorId").equalTo(therapistId) //TODO Change the method

    }


    override fun getUserType(id: String, onResult: (String) -> Unit) {
        database.reference.child(KEY_USER).child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userType = snapshot.child(KEY_TYPE_USER).value
                onResult(userType.toString())
            }
            override fun onCancelled(error: DatabaseError) = Unit
        })
    }

    override fun getDoctorProfile(id: String, onResult: (DoctorEntity) -> Unit) {
        database.reference.child(KEY_USER).child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(DoctorEntity::class.java)
                if (user?.name != null) {
                    user.run {
                        onResult(DoctorEntity(id, name, lastName, email, type))
                    }
                } else {
                    onResult(DoctorEntity())
                }

            }

            override fun onCancelled(error: DatabaseError) = Unit
        })
    }

    override fun getPatientProfile(id: String, onResult: (PatientEntity) -> Unit) {
        database.reference.child(KEY_USER).child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(PatientEntity::class.java)
                user?.run {
                    onResult(
                        PatientEntity(
                            id,
                            name,
                            lastName,
                            email,
                            type,
                            age,
                            treatment,
                            weight,
                            affectation,
                            doctorId
                        )
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) = Unit
        })
    }

    override fun getTherapistProfile(id: String, onResult: (TherapistEntity) -> Unit) {
        database.reference.child(KEY_USER).child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(TherapistEntity::class.java)
                if (user?.name != null) {
                    user.run {
                        onResult(TherapistEntity(id, name, lastName, email, type, specialty,doctorId))
                    }
                } else {
                    onResult(TherapistEntity())
                }

            }

            override fun onCancelled(error: DatabaseError) = Unit
        })
    }


    override fun createPatient(patientEntity: PatientEntity) {
        database.reference.child(KEY_USER).child(patientEntity.id).setValue(patientEntity)
    }

    override fun createTherapist(therapistEntity: TherapistEntity) {
        database.reference.child(KEY_USER).child(therapistEntity.id).setValue(therapistEntity)
    }

    override fun createDoctor(doctorEntity: DoctorEntity) {
        database.reference.child(KEY_USER).child(doctorEntity.id).setValue(doctorEntity)
    }

    override fun getAllDoctors(onResult: (List<DoctorEntity>) -> Unit) {
        database.reference.child(KEY_USER).orderByChild(KEY_TYPE_USER).equalTo(KEY_DOCTOR)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) = onResult(listOf())

                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.run {
                        val doctors = children.mapNotNull { it.getValue(DoctorEntity::class.java) }
                        onResult(doctors.map(DoctorEntity::mapToDoctor))
                    }
                }
            })
    }

    override fun getPatientsByDoctor(doctorId: String, onResult: (List<PatientEntity>) -> Unit) {
        //TODO See why is not implemented
    }
    override fun getTherapistByDoctor(doctorId: String, onResult: (List<TherapistEntity>) -> Unit) {
        //TODO See why is not implemented
    }





    override fun listenToTherapistByDoctor(doctorId: String, onResult: (TherapistEntity) -> Unit) {
        database.reference.child(KEY_USER).orderByChild("doctorId").equalTo(doctorId)
            .addChildEventListener(object : ChildEventListener {
                override fun onCancelled(error: DatabaseError) = Unit

                override fun onChildMoved(snapshot: DataSnapshot, p1: String?) = Unit

                override fun onChildChanged(snapshot: DataSnapshot, p1: String?) {
                    snapshot.getValue(TherapistEntity::class.java)?.run {
                        if (isValid()) {
                            Log.i(
                                "therapistInfo",
                                "${this.name} ${this.email} ${this.lastName} ${snapshot.key} ${this.type}"
                            )
                            onResult(mapToTherapist())
                        }
                    }
                }

                override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
                    snapshot.getValue(TherapistEntity::class.java)?.run {
                        if (isValid()) {
                            Log.i(
                                "therapistInfo",
                                "${this.name} ${this.email} ${this.lastName} ${snapshot.key} ${this.type}"
                            )
                            onResult(mapToTherapist())
                        }
                    }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) = Unit
            })
    }

    override fun listenToDoctors(onResult: (DoctorEntity) -> Unit) {
        database.reference.child(KEY_USER)
            .orderByChild(KEY_TYPE_USER).equalTo(KEY_DOCTOR)
            .addChildEventListener(object : ChildEventListener {
                override fun onCancelled(error: DatabaseError) = Unit
                override fun onChildMoved(snapshot: DataSnapshot, p1: String?) = Unit

                override fun onChildChanged(snapshot: DataSnapshot, p1: String?) {
                    snapshot.getValue(DoctorEntity::class.java)?.run {
                        if (isValid()) {
                            Log.i(
                                "doctorInfo",
                                "${this.name} ${this.email} ${this.lastName} ${snapshot.key} ${this.type}"
                            )
                            onResult(mapToDoctor())
                        }
                    }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) = Unit

                override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
                    snapshot.getValue(DoctorEntity::class.java)?.run {
                        if (isValid()) {
                            Log.i(
                                "doctorInfo",
                                "${this.name} ${this.email} ${this.lastName} ${snapshot.key} ${this.type}"
                            )
                            onResult(mapToDoctor())
                        }
                    }
                }
            })
    }

    override fun listenToPatientByDoctor(doctorId: String, onResult: (PatientEntity) -> Unit) {
        database.reference.child(KEY_USER).orderByChild("doctorId").equalTo(doctorId)
            .addChildEventListener(object : ChildEventListener {
                override fun onCancelled(error: DatabaseError) = Unit

                override fun onChildMoved(snapshot: DataSnapshot, p1: String?) = Unit

                override fun onChildChanged(snapshot: DataSnapshot, p1: String?) {
                    snapshot.getValue(PatientEntity::class.java)?.run {
                        if (isValid()) {
                            Log.i(
                                "patientInfo",
                                "${this.name} ${this.email} ${this.lastName} ${snapshot.key} ${this.type}"
                            )
                            onResult(mapToPatient())
                        }
                    }
                }

                override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
                    snapshot.getValue(PatientEntity::class.java)?.run {
                        if (isValid()) {
                            Log.i(
                                "patientInfo",
                                "${this.name} ${this.email} ${this.lastName} ${snapshot.key} ${this.type}"
                            )
                            onResult(mapToPatient())
                        }
                    }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) = Unit
            })
    }


    override fun deleteUser(userId: String, onResult: (Boolean) -> Unit) {
        database.reference.child(KEY_USER).child(userId).removeValue().addOnCompleteListener {
            onResult(it.isSuccessful && it.isComplete)
        }

    }
}