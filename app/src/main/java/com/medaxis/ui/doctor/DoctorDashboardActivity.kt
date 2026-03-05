package com.medaxis.ui.doctor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.medaxis.databinding.ActivityDoctorDashboardBinding

class DoctorDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvStats.text = "Pending Patients: 7\nToday's Appointments: 12\nCompleted: 21"
    }
}
