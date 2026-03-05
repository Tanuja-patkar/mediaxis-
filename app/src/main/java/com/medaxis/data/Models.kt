package com.medaxis.data

data class User(
    val id: String = "",
    val name: String = "",
    val role: String = "patient",
    val mobile: String = ""
)

data class Doctor(
    val id: String = "",
    val name: String = "",
    val degree: String = "",
    val specialization: String = "",
    val experienceYears: Int = 0,
    val hospitalName: String = "",
    val mobile: String = ""
)

data class Patient(
    val id: String = "",
    val name: String = "",
    val dob: String = "",
    val address: String = "",
    val disease: String = "",
    val emergencyContact: String = "",
    val mobile: String = ""
)

data class Appointment(
    val id: String = "",
    val patientId: String = "",
    val patientName: String = "",
    val doctorId: String = "",
    val doctorName: String = "",
    val disease: String = "",
    val date: String = "",
    val time: String = "",
    val status: String = "pending"
)

data class Prescription(
    val id: String = "",
    val appointmentId: String = "",
    val doctorName: String = "",
    val patientName: String = "",
    val medicineName: String = "",
    val dosage: String = "",
    val days: Int = 0,
    val notes: String = ""
)
