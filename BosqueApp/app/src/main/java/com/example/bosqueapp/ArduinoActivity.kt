package com.example.bosqueapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ArduinoActivity : AppCompatActivity() {

    private lateinit var temperatureTextView: TextView
    private lateinit var humidityTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.arduino)

        //  TextView en el layout
        temperatureTextView = findViewById(R.id.temperatureTextView)
        humidityTextView = findViewById(R.id.humidityTextView)

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

        // Conectar a Firebase y escuchar cambios
        val database = FirebaseDatabase.getInstance()
        val tempRef = database.getReference("datos/Temp/Temperatura")
        val humRef = database.getReference("datos/Hum/Humedad")

        // Escuchar cambios en temperatura
        tempRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val temperature = snapshot.getValue(Double::class.java)
                temperatureTextView.text = "Temperatura: ${temperature ?: "--"}°C"
            }

            override fun onCancelled(error: DatabaseError) {
                temperatureTextView.text = "Error al leer temperatura"
            }
        })

        // Escuchar cambios en humedad
        humRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val humidity = snapshot.getValue(Double::class.java)
                humidityTextView.text = "Humedad: ${humidity ?: "--"}%"
            }

            override fun onCancelled(error: DatabaseError) {
                humidityTextView.text = "Error al leer humedad"
            }
        })
    }
}
