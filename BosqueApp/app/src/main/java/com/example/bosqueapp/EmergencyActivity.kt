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

        // Inicializar botones de navegación
        val userButton: ImageButton = findViewById(R.id.userButton)
        val homeButton: ImageButton = findViewById(R.id.homeButton)
        val notificationsButton: ImageButton = findViewById(R.id.notificationsButton)

        // Configurar listeners para la navegación
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

    // Funciones para llamadas de emergencia
    fun callBomberos(view: View) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:132") // Número de bomberos
        startActivity(intent)
    }

    fun callCarabineros() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:133") // Número de carabineros
        startActivity(intent)
    }

    fun callAmbulance(view: View) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:131") // Número de ambulancia
        startActivity(intent)
    }

    fun callConaf(view: View) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:130") // Número de CONAF
        startActivity(intent)
    }
}