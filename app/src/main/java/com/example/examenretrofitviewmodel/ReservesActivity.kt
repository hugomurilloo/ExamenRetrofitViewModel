package com.example.examenretrofitviewmodel

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReservesActivity : AppCompatActivity() {

    private val viewModel: MaterialsViewModel by viewModels()


    private lateinit var adapter: ReservaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserves)



        val userId = intent.getIntExtra("USER_ID", 0)

        val rv = findViewById<RecyclerView>(R.id.rvReserves)
        adapter = ReservaAdapter()
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        viewModel.setUserId(userId)

        viewModel.reserves.observe(this) { llista ->
            if (llista.isEmpty()) {
                Toast.makeText(this, "No tens reserves", Toast.LENGTH_SHORT).show()
            }
            adapter.submitList(llista)
        }

        viewModel.carregarReservesUsuari()
    }
}