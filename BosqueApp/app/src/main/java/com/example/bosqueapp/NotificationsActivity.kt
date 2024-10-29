package com.example.bosqueapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NotificationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notificacion)

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
            // Mostrar un Toast indicando que ya estás en NotificationsActivity
            Toast.makeText(this, "Ya estás aquí", Toast.LENGTH_SHORT).show()
        }
    }
}
