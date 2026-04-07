package com.example.examenretrofitviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MaterialsViewModel : ViewModel() {

    private val _reserves = MutableLiveData<List<Reserva>>()
    val reserves: LiveData<List<Reserva>> = _reserves

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private var userId: Int = 0

    fun setUserId(id: Int) { userId = id }


    fun carregarReservesUsuari() {
        if (userId == 0) return
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = ItemAPI.API().obtenirReservesUsuari(userId)
                if (response.isSuccessful) {
                    _reserves.value = response.body() ?: emptyList()
                } else {
                    _message.value = "❌ Error reserves: ${response.code()}"
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error en reserves", e)
                _message.value = "❌ Error de xarxa"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // --- FUNCIÓN CORREGIDA AQUÍ ---
    fun crearReserva(idmaterial: Int, datareserva: String, datafinal: String) {
        if (userId == 0) {
            _message.value = "❌ Usuari no identificat"
            return
        }
        _isLoading.value = true

        // Usamos parámetros nombrados para evitar el error de tipo (String vs Int)
        // No pasamos el 'id' porque el servidor lo genera automáticamente
        val reserva = Reserva(
            idusuari = userId,
            idmaterial = idmaterial,
            datareserva = datareserva,
            datafinal = datafinal
        )

        viewModelScope.launch {
            try {
                val response = ItemAPI.API().crearReserva(reserva)
                if (response.isSuccessful) {
                    _message.value = "✅ Reserva creada correctament!"
                    // Opcional: Recargar las reservas después de crear una nueva
                    carregarReservesUsuari()
                } else {
                    _message.value = "❌ Error al crear: ${response.code()}"
                }
            } catch (e: Exception) {
                _message.value = "❌ Error de sistema"
            } finally {
                _isLoading.value = false
            }
        }
    }
}