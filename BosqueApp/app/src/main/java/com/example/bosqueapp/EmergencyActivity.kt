package com.example.bosqueapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class EmergencyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.emergencias_contactos)

        // barra de navegación
        val userButton: ImageButton = findViewById(R.id.userButton)
        val homeButton: ImageButton = findViewById(R.id.homeButton)
        val notificationsButton: ImageButton = findViewById(R.id.notificationsButton)

        userButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        notificationsButton.setOnClickListener {
            val intent = Intent(this, NotificationsActivity::class.java)
            startActivity(intent)
        }
    }

    // funciones para llamadas de emergencia
    // se han suprimido las alertas "UNUSED_PARAMETER", ya que los parametros SI se están usando.
    fun callBomberos(@Suppress("UNUSED_PARAMETER") view: View) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:132") // Número de bomberos
        startActivity(intent)
    }

    fun callCarabineros() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:133") // Número de carabineros
        startActivity(intent)
    }

    fun callAmbulance(@Suppress("UNUSED_PARAMETER") view: View) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:131") // Número de ambulancia
        startActivity(intent)
    }

    fun callConaf(@Suppress("UNUSED_PARAMETER") view: View) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:130") // Número de CONAF
        startActivity(intent)
    }
}