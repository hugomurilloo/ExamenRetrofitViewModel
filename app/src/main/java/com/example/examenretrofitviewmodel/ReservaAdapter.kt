package com.example.examenretrofitviewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale
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
        val item = reserves[position]
        holder.bind(reserves[position])

        Glide.with(holder.itemView.context)
            .load(item.imatge)
            .placeholder(R.drawable.ic_lock)
            .error(R.drawable.ic_trash)
            .into(holder.ivMaterial)
    }

    override fun getItemCount() = reserves.size

    class ReservaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMaterial = view.findViewById<TextView>(R.id.tvReservaMaterial)
        val tvDates = view.findViewById<TextView>(R.id.tvReservaDates)

        val ivMaterial = view.findViewById<ImageView>(R.id.ivMaterial)

        //toDisplayDate
        fun String.toDisplayDate(): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return try {
                val date = inputFormat.parse(this)
                outputFormat.format(date!!)
            } catch (e: Exception) {
                this
            }
        }

        fun bind(reserva: Reserva) {
            tvMaterial.text = reserva.descripcio ?: "Material #${reserva.idmaterial}"
            // Limpiamos la fecha que viene con T00:00:00
            val inici = reserva.datareserva.toDisplayDate()
            val fi = reserva.datafinal.toDisplayDate()
            tvDates.text = "$inici  ➔  $fi"
        }
    }
}