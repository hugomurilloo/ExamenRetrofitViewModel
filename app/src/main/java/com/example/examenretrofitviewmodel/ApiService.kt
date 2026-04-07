package com.example.examenretrofitviewmodel

import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // LOGIN
    @POST("login/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>


    // RESERVES
    @POST("reserves/")
    suspend fun crearReserva(@Body reserva: Reserva): Response<Reserva>

    @GET("reserves/usuari/{idusuari}")
    suspend fun obtenirReservesUsuari(@Path("idusuari") idusuari: Int): Response<List<Reserva>>
}