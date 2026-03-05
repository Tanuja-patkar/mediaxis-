package com.medaxis.utils

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import com.medaxis.data.Appointment
import java.io.File
import java.io.FileOutputStream

object PdfGenerator {
    fun generateAppointmentReceipt(context: Context, appointment: Appointment, bookingId: String): File {
        val doc = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(300, 500, 1).create()
        val page = doc.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint().apply { textSize = 12f }

        canvas.drawText("MedAxis Appointment Receipt", 20f, 30f, paint)
        canvas.drawText("Patient: ${appointment.patientName}", 20f, 70f, paint)
        canvas.drawText("Doctor: ${appointment.doctorName}", 20f, 95f, paint)
        canvas.drawText("Date: ${appointment.date}", 20f, 120f, paint)
        canvas.drawText("Time: ${appointment.time}", 20f, 145f, paint)
        canvas.drawText("Booking ID: $bookingId", 20f, 170f, paint)

        doc.finishPage(page)

        val file = File(context.getExternalFilesDir(null), "appointment_$bookingId.pdf")
        doc.writeTo(FileOutputStream(file))
        doc.close()
        return file
    }
}
