package com.medaxis.ui.appointment

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.medaxis.data.Appointment
import com.medaxis.data.FirebaseRepository
import com.medaxis.databinding.ActivityBookAppointmentBinding
import com.medaxis.utils.PdfGenerator
import java.util.UUID

class BookAppointmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookAppointmentBinding
    private val repository = FirebaseRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val doctors = listOf("Dr. Sarah Khan (Cardiologist)", "Dr. Vivek Rao (Dermatologist)", "Dr. Ananya Das (General)")
        val slots = listOf("10:00 AM", "11:30 AM", "02:00 PM", "04:30 PM")
        binding.spinnerDoctor.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, doctors)
        binding.spinnerTime.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, slots)

        binding.btnConfirmAppointment.setOnClickListener {
            val bookingId = UUID.randomUUID().toString().take(8).uppercase()
            val appointment = Appointment(
                patientName = binding.etPatientName.text.toString(),
                doctorName = binding.spinnerDoctor.selectedItem.toString(),
                date = binding.etDate.text.toString(),
                time = binding.spinnerTime.selectedItem.toString(),
                disease = binding.etDisease.text.toString()
            )
            repository.createAppointment(appointment) { success ->
                if (success) {
                    PdfGenerator.generateAppointmentReceipt(this, appointment, bookingId)
                    Snackbar.make(binding.root, "Appointment confirmed. Receipt generated.", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}
