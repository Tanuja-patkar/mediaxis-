package com.medaxis.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.medaxis.data.FirebaseRepository
import com.medaxis.databinding.ActivityAuthBinding
import com.medaxis.ui.doctor.DoctorDashboardActivity
import com.medaxis.ui.patient.PatientDashboardActivity

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private val repository = FirebaseRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPatientLogin.setOnClickListener { handleAuth("patient") }
        binding.btnDoctorLogin.setOnClickListener { handleAuth("doctor") }
    }

    private fun handleAuth(role: String) {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        if (email.isBlank() || password.isBlank()) return

        repository.login(email, password) { success, _ ->
            if (success) {
                navigate(role)
            } else {
                showSignupDialog(role, email, password)
            }
        }
    }

    private fun showSignupDialog(role: String, email: String, password: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("No account found")
            .setMessage("Create a new $role account?")
            .setPositiveButton("Signup") { _, _ ->
                val payload = mapOf("name" to binding.etName.text.toString(), "mobile" to binding.etMobile.text.toString())
                repository.signup(email, password, role, payload) { success, _ -> if (success) navigate(role) }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun navigate(role: String) {
        val intent = if (role == "doctor") Intent(this, DoctorDashboardActivity::class.java)
        else Intent(this, PatientDashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}
