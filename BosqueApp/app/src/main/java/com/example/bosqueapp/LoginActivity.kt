package com.example.bosqueapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio_sesion)

        val loginButton: Button = findViewById(R.id.loginButton)
        val usernameInput: TextInputEditText = findViewById(R.id.usernameInput)
        val passwordInput: TextInputEditText = findViewById(R.id.passwordInput)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            //acá se hace uso del try catch!
            try {
                if (authenticateUser(username, password)) {
                    startActivity(Intent(this, MainMenuActivity::class.java))
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error inesperado: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
    // esta funcion es la que guarda el usuario y la contraseña
    private fun authenticateUser(username: String, password: String): Boolean {
        val validUsername = "admin"
        val validPassword = "password"
        return username == validUsername && password == validPassword
    }
}
