package com.example.examenretrofitviewmodel

data class Usuari(
    val id: Int? = null,
    val nom: String,
    val rol: String,
    val password: String
)

data class LoginRequest(
    val nom: String,
    val password: String
)

data class LoginResponse(
    val id: Int,
    val nom: String,
    val rol: String
)