package com.medaxis.utils

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.FirebaseFirestore

class EmergencyManager(private val context: Context) {
    private val fusedLocation = LocationServices.getFusedLocationProviderClient(context)
    private val db = FirebaseFirestore.getInstance()

    @SuppressLint("MissingPermission")
    fun sendEmergencyAlert(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        fusedLocation.lastLocation
            .addOnSuccessListener { location ->
                if (location == null) {
                    onFailure("Location unavailable. Please enable GPS.")
                    return@addOnSuccessListener
                }

                val alert = mapOf(
                    "patientName" to "Current User",
                    "lat" to location.latitude,
                    "lng" to location.longitude,
                    "status" to "pending"
                )

                db.collection("EmergencyAlerts").add(alert)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFailure(it.message ?: "Failed to send alert") }
            }
            .addOnFailureListener { onFailure(it.message ?: "Location error") }
    }
}
