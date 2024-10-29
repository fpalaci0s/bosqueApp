package com.example.bosqueapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ZoneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.zonas)

        val userButton: ImageButton = findViewById(R.id.userButton)
        val homeButton: ImageButton = findViewById(R.id.homeButton)
        val notificationsButton: ImageButton = findViewById(R.id.notificationsButton)

        userButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener {
            val intent = Intent(this, EmergencyActivity::class.java)
            startActivity(intent)
        }

        notificationsButton.setOnClickListener {
            val intent = Intent(this, NotificationsActivity::class.java)
            startActivity(intent)
        }
    }
}
