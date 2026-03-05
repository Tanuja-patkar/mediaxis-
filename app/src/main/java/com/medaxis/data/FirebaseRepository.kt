package com.medaxis.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun login(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onComplete(true, null) }
            .addOnFailureListener { onComplete(false, it.message) }
    }

    fun signup(email: String, password: String, role: String, payload: Map<String, Any>, onComplete: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid ?: return@addOnSuccessListener
                db.collection("Users").document(uid)
                    .set(mapOf("role" to role, "email" to email) + payload)
                    .addOnSuccessListener { onComplete(true, null) }
                    .addOnFailureListener { onComplete(false, it.message) }
            }
            .addOnFailureListener { onComplete(false, it.message) }
    }

    fun createAppointment(appointment: Appointment, onComplete: (Boolean) -> Unit) {
        db.collection("Appointments").add(appointment)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    fun savePrescription(prescription: Prescription, onComplete: (Boolean) -> Unit) {
        db.collection("Prescriptions").add(prescription)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }
}
