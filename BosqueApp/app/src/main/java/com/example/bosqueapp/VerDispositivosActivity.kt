package com.example.bosqueapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bosqueapp.Adapter.AdapterDispositivo
import com.example.bosqueapp.Models.Dispositivo
import com.example.bosqueapp.databinding.VerDispositivosBinding
import com.google.firebase.database.FirebaseDatabase

class VerDispositivosActivity : AppCompatActivity() {

    private lateinit var binding: VerDispositivosBinding
    private lateinit var adapter: AdapterDispositivo
    private val dispositivosList = ArrayList<Dispositivo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = VerDispositivosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // recyclerview
        binding.devicesRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdapterDispositivo(
            dispositivosList,
            onEditClick = { dispositivo ->
                val intent = Intent(this, EditarDispositivoActivity::class.java).apply {
                    putExtra("id", dispositivo.id)
                    putExtra("nombre", dispositivo.nombre)
                    putExtra("tipo", dispositivo.tipo)
                    putExtra("descripcion", dispositivo.descripcion)
                }
                startActivity(intent)
            },
            onDeleteClick = { dispositivo ->
                eliminarDispositivo(dispositivo)
            }
        )
        binding.devicesRecyclerView.adapter = adapter

        // Firebase
        cargarDispositivos()

    //
    binding.btnVolver.setOnClickListener {
        finish()
    }

    }
    private fun cargarDispositivos() {
        val database = FirebaseDatabase.getInstance().getReference("Dispositivos")
        database.get().addOnSuccessListener { snapshot ->
            dispositivosList.clear()
            snapshot.children.forEach {
                val dispositivo = it.getValue(Dispositivo::class.java)
                dispositivo?.let { dispositivosList.add(it) }
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun eliminarDispositivo(dispositivo: Dispositivo) {
        val database = FirebaseDatabase.getInstance().getReference("Dispositivos")
        database.child(dispositivo.id!!).removeValue()
            .addOnSuccessListener {
                Toast.makeText(this, "Dispositivo eliminado", Toast.LENGTH_SHORT).show()
                dispositivosList.remove(dispositivo)
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show()
            }
    }
}
