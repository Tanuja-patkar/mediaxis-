package com.medaxis

import android.app.Application
import com.google.firebase.FirebaseApp

class MedAxisApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
