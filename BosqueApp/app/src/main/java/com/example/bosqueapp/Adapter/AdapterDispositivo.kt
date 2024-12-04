package com.example.bosqueapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bosqueapp.Models.Dispositivo
import com.example.bosqueapp.R

class AdapterDispositivo(
    private var dispositivos: ArrayList<Dispositivo>,
    private val onEditClick: (Dispositivo) -> Unit,
    private val onDeleteClick: (Dispositivo) -> Unit
) : RecyclerView.Adapter<AdapterDispositivo.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tipo: TextView = itemView.findViewById(R.id.tvTipo)
        val descripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val btnEditar: View = itemView.findViewById(R.id.btnEditaritem)
        val btnEliminar: View = itemView.findViewById(R.id.btnEliminaritem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDispositivo.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dispositivos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dispositivo = dispositivos[position]

        holder.nombre.text = dispositivo.nombre
        holder.tipo.text = dispositivo.tipo
        holder.descripcion.text = dispositivo.descripcion

        // Botones!!
        holder.btnEditar.setOnClickListener {
            onEditClick(dispositivo)
        }

        holder.btnEliminar.setOnClickListener {
            onDeleteClick(dispositivo)
        }
    }

    override fun getItemCount(): Int {
        return dispositivos.size
    }
}
