package com.example.bosqueapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bosqueapp.databinding.CrearDispositivoBinding
import com.example.bosqueapp.Models.Dispositivo
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CrearDispositivoActivity : AppCompatActivity() {

    private lateinit var binding: CrearDispositivoBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CrearDispositivoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Dispositivos")

        binding.saveDeviceButton.setOnClickListener {
            if (validarCampos()) {
                guardarDispositivoEnFirebase()
            }
        }

        // Configurar acción del botón "Ver Dispositivos"
        binding.viewDeviceButton.setOnClickListener {
            val intent = Intent(this, VerDispositivosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validarCampos(): Boolean {
        var isValid = true
        if (binding.deviceNameInput.text.isNullOrEmpty()) {
            binding.deviceNameInput.error = "Este campo es obligatorio"
            isValid = false
        }
        if (binding.deviceTypeInput.text.isNullOrEmpty()) {
            binding.deviceTypeInput.error = "Este campo es obligatorio"
            isValid = false
        }
        if (binding.deviceDescriptionInput.text.isNullOrEmpty()) {
            binding.deviceDescriptionInput.error = "Este campo es obligatorio"
            isValid = false
        }
        return isValid
    }

    private fun guardarDispositivoEnFirebase() {
        val nombre = binding.deviceNameInput.text.toString()
        val tipo = binding.deviceTypeInput.text.toString()
        val descripcion = binding.deviceDescriptionInput.text.toString()

        val id = database.push().key ?: return
        val dispositivo = Dispositivo(id, nombre, tipo, descripcion)

        database.child(id).setValue(dispositivo)
            .addOnSuccessListener {
                Snackbar.make(
                    binding.root,
                    "Dispositivo guardado correctamente",
                    Snackbar.LENGTH_SHORT
                ).show()
                limpiarCampos()
            }
            .addOnFailureListener {
                Snackbar.make(binding.root, "Error al guardar dispositivo", Snackbar.LENGTH_SHORT)
                    .show()
            }
    }

    private fun limpiarCampos() {
        binding.deviceNameInput.setText("")
        binding.deviceTypeInput.setText("")
        binding.deviceDescriptionInput.setText("")
    }
}
