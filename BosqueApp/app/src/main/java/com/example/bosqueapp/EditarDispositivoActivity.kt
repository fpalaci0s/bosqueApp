package com.example.bosqueapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bosqueapp.Models.Dispositivo
import com.example.bosqueapp.databinding.EditarDispositivoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditarDispositivoActivity : AppCompatActivity() {

    private lateinit var binding: EditarDispositivoBinding
    private lateinit var database: DatabaseReference
    private lateinit var dispositivoId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditarDispositivoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Dispositivos")

        //  intent
        dispositivoId = intent.getStringExtra("id") ?: return
        binding.deviceNameInput.setText(intent.getStringExtra("nombre"))
        binding.deviceTypeInput.setText(intent.getStringExtra("tipo"))
        binding.deviceDescriptionInput.setText(intent.getStringExtra("descripcion"))

        binding.saveDeviceButton.setOnClickListener {
            if (validarCampos()) {
                actualizarDispositivo()
            }
        }
        //
        binding.backButton.setOnClickListener {
            finish()
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

    private fun actualizarDispositivo() {
        val nombre = binding.deviceNameInput.text.toString()
        val tipo = binding.deviceTypeInput.text.toString()
        val descripcion = binding.deviceDescriptionInput.text.toString()

        val dispositivoActualizado = Dispositivo(dispositivoId, nombre, tipo, descripcion)
        database.child(dispositivoId).setValue(dispositivoActualizado)
            .addOnSuccessListener {
                Toast.makeText(this, "Dispositivo actualizado correctamente", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al actualizar dispositivo", Toast.LENGTH_SHORT).show()
            }
    }
}
