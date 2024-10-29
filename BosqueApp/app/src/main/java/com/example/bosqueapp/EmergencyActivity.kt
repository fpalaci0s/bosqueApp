package com.example.bosqueapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
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
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error inesperado: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    fun callCarabineros() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:133") // Número de carabineros
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error inesperado: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    fun callAmbulance(@Suppress("UNUSED_PARAMETER") view: View) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:131") // Número de ambulancia
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error inesperado: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    fun callConaf(@Suppress("UNUSED_PARAMETER") view: View) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:130") // Número de CONAF
        try {
            startActivity(intent)
        } catch (e: Exception) {
            // Manejar el error si es necesario
            // Puedes mostrar un mensaje al usuario
        }
    }
}
