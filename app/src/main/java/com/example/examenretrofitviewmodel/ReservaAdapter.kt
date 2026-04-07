package com.example.examenretrofitviewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.text.split

class ReservaAdapter : RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder>() {

    private var reserves = listOf<Reserva>()

    fun submitList(newList: List<Reserva>) {
        reserves = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reserva, parent, false)
        return ReservaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
        holder.bind(reserves[position])
    }

    override fun getItemCount() = reserves.size

    class ReservaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMaterial = view.findViewById<TextView>(R.id.tvReservaMaterial)
        val tvDates = view.findViewById<TextView>(R.id.tvReservaDates)

        fun bind(reserva: Reserva) {
            tvMaterial.text = reserva.descripcio ?: "Material #${reserva.idmaterial}"
            // Limpiamos la fecha que viene con T00:00:00
            val inici = reserva.datareserva.split("T")[0]
            val fi = reserva.datafinal.split("T")[0]
            tvDates.text = "$inici  ➔  $fi"
        }
    }
}