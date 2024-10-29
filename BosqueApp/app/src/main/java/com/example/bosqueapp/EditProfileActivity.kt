package com.example.bosqueapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.annotation.SuppressLint

class EditProfileActivity : AppCompatActivity() {
    // se almacenan las referencias
    private lateinit var nameEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var phoneEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editar_perfil)

        // Referencias a los campos de texto
        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        phoneEditText = findViewById(R.id.phoneEditText)

        // Configurar el prefijo +569
        setupPhoneEditText()

        // Cargar datos actuales!!
        loadProfileData()

        // Botón de guardar.
        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            val newName = nameEditText.text.toString()
            val newEmail = emailEditText.text.toString()
            val newPhone = phoneEditText.text.toString()

            // Validación del número de teléfono
            if (!isValidChileanPhoneNumber(newPhone)) {
                Toast.makeText(this, "Número de teléfono inválido. Debe comenzar con +569 y tener 8 dígitos adicionales.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Formatear el número de teléfono antes de guardar (épico)
            val formattedPhone = formatChileanPhoneNumber(newPhone)

            // Guardar los cambios en SharedPreferences ( si
            saveProfileData(newName, newEmail, formattedPhone)

            Toast.makeText(this, "Perfil actualizado", Toast.LENGTH_SHORT).show()

            // Volver a la actividad anterior con los datos actualizados (ayayayy)
            setResult(RESULT_OK, Intent())
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupPhoneEditText() {
        phoneEditText.setText("+569")  // Establecer el prefijo
        phoneEditText.setSelection(phoneEditText.text?.length ?: 0)  // Colocar el cursor al final¿

        // prevenir que se modifique el prefijo. ( dolor )
        phoneEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().startsWith("+569")) {
                    phoneEditText.setText("+569")
                    phoneEditText.setSelection(phoneEditText.text?.length ?: 0)
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun loadProfileData() { // esto carga
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        nameEditText.setText(sharedPreferences.getString("name", ""))
        emailEditText.setText(sharedPreferences.getString("email", ""))
        phoneEditText.setText(sharedPreferences.getString("phone", "+569"))  // configurar +569
    }

    private fun saveProfileData(name: String, email: String, phone: String) { // esto guarda
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("name", name)
            putString("email", email)
            putString("phone", phone)
            apply()
        }
    }

    private fun isValidChileanPhoneNumber(phone: String): Boolean { // esto verifica si el numero es chileno.
        // Permitir +569 seguido de un espacio opcional y luego 8 dígitos con o sin espacios
        val chileanPhonePattern = "^\\+569 ?(\\d ?){8}$".toRegex()
        return phone.matches(chileanPhonePattern)
    }

    private fun formatChileanPhoneNumber(phone: String): String {
        // Remover todos los espacios y formatear como +569 1234 5678,
        val cleanedPhone = phone.replace("\\s".toRegex(), "")
        return "+569 ${cleanedPhone.substring(4, 8)} ${cleanedPhone.substring(8)}"
    }
}
