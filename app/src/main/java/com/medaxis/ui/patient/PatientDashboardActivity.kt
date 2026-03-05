package com.medaxis.ui.patient

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.medaxis.databinding.ActivityPatientDashboardBinding
import com.medaxis.ui.appointment.BookAppointmentActivity
import com.medaxis.ui.notification.NotificationActivity
import com.medaxis.ui.profile.ProfileActivity
import com.medaxis.utils.EmergencyManager

class PatientDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPatientDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardBook.setOnClickListener { startActivity(Intent(this, BookAppointmentActivity::class.java)) }
        binding.cardHospitals.setOnClickListener { startActivity(Intent(this, NearbyHospitalsActivity::class.java)) }
        binding.cardEmergency.setOnClickListener { triggerEmergency() }

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                com.medaxis.R.id.nav_notifications -> startActivity(Intent(this, NotificationActivity::class.java))
                com.medaxis.R.id.nav_profile -> startActivity(Intent(this, ProfileActivity::class.java))
            }
            true
        }
    }

    private fun triggerEmergency() {
        EmergencyManager(this).sendEmergencyAlert(
            onSuccess = {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Emergency Sent")
                    .setMessage("Nearby hospitals alerted. Ambulance is on the way.")
                    .setPositiveButton("OK", null)
                    .show()
            },
            onFailure = {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Unable to send")
                    .setMessage(it)
                    .setPositiveButton("OK", null)
                    .show()
            }
        )
    }
}
