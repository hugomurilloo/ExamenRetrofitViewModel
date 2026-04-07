package com.example.examenretrofitviewmodel

data class Reserva(
    val id: Int? = null,
    val idusuari: Int,
    val idmaterial: Int,
    val datareserva: String,
    val datafinal: String,
    val descripcio: String? = null,
    val imatge: String? = null
)