package com.example.bosqueapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ProfileActivity : AppCompatActivity() {

    private lateinit var usernameText: TextView
    private lateinit var emailText: TextView
    private lateinit var phoneText: TextView
    private lateinit var editProfileLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil)

        // inicializa los elementos de vista
        usernameText = findViewById(R.id.usernameText)
        emailText = findViewById(R.id.emailText)
        phoneText = findViewById(R.id.phoneText)
        val editProfileButton: MaterialButton = findViewById(R.id.editProfileButton)

        // cargar datos guardados en SharedPreferences
        loadProfileData()

        // Inicializa el ActivityResultLauncher
        editProfileLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                loadProfileData() // Recargar los datos después de la edición
            }
        }

        // botón de editar perfil
        editProfileButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            editProfileLauncher.launch(intent)
        }

        // barra de navegación
        val userButton: ImageButton = findViewById(R.id.userButton)
        val homeButton: ImageButton = findViewById(R.id.homeButton)
        val notificationsButton: ImageButton = findViewById(R.id.notificationsButton)

        userButton.setOnClickListener {
            // mostrar un Toast indicando que ya estás en profileActivity
            Toast.makeText(this, "Ya estás aquí", Toast.LENGTH_SHORT).show()
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
    //esto cargaa los datos de perfil
    private fun loadProfileData() {
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        // El Context.MODE_PRIVATE se usa para guardar las preferencias del usuario... según las noticias
        usernameText.text = sharedPreferences.getString("name", "Tu nombre de usuario")
        emailText.text = sharedPreferences.getString("email", "Tu correo electrónico")
        phoneText.text = sharedPreferences.getString("phone", "X")
    }
}
