# MedAxis Android App

MedAxis is a modern Android medical appointment app built with Kotlin, Material Design 3, Firebase, and card-based dashboard flows inspired by production healthcare apps.

## Implemented Core Modules
- Splash + animated branding with Lottie.
- Unified Login/Signup for patient and doctor roles.
- Patient dashboard with modern cards and emergency alert trigger.
- Doctor dashboard with stats and workflow actions.
- Appointment booking flow with receipt PDF generation.
- FCM-based push notification service.
- Firestore persistence layer for users, appointments, prescriptions, and emergency alerts.
- Nearby hospitals screen placeholder for Google Maps integration.
- Dark mode-ready Material3 theming.

## Firebase Collections
- `Users`
- `Doctors`
- `Patients`
- `Appointments`
- `Prescriptions`
- `EmergencyAlerts`

## Setup
1. Open in Android Studio Iguana or newer.
2. Add your `google-services.json` under `app/`.
3. Sync Gradle and run on Android 8.0+ device.
4. Configure Maps API key and FCM in Firebase console.

## Notes
- Emergency alert stores GPS coordinates and alert status to Firestore.
- Appointment confirmation generates a local PDF receipt in external app files.
