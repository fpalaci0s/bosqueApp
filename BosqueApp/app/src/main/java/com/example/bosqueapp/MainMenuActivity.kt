package com.example.bosqueapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import android.content.Context
import android.widget.Toast

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        // inicializar
        val cardProfile: MaterialCardView = findViewById(R.id.cardProfile)
        val cardEmergencyContacts: MaterialCardView = findViewById(R.id.cardEmergencyContacts)
        val cardVinculacion: MaterialCardView = findViewById(R.id.cardVinculacion)
        val cardZone: MaterialCardView = findViewById(R.id.cardZone)
        val cardNotifications: MaterialCardView = findViewById(R.id.cardNotifications)
        val btnLogout: MaterialButton = findViewById(R.id.fabLogout)

        // acción para la tarjeta de perfil
        cardProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error inesperado: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

        // acción para la tarjeta de contactos de emergencia
        cardEmergencyContacts.setOnClickListener {
            val intent = Intent(this, EmergencyActivity::class.java)
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error inesperado: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

        // acción para la tarjeta de vinculación
        cardVinculacion.setOnClickListener {
            val intent = Intent(this, VinculationActivity::class.java)
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error inesperado: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

        // acción para la tarjeta de zonas
        cardZone.setOnClickListener {
            val intent = Intent(this, ArduinoActivity::class.java)
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error inesperado: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

        // acción para la tarjeta de notificaciones
        cardNotifications.setOnClickListener {
            val intent = Intent(this, NotificationsActivity::class.java)
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error inesperado: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

        // acción para el botón de cerrar sesión
        btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        // se borra los datos de usuario de SharedPreferences, después de cerrar sesión con exito
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }

        // redirigir a la pantalla de inicio de sesión
        val loginIntent = Intent(this, LoginActivity::class.java)
        loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        try {
            startActivity(loginIntent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error inesperado: ${e.message}", Toast.LENGTH_LONG).show()
        }
        finish()
    }
}
