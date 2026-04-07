package com.example.examenretrofitviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginSuccess = MutableLiveData<Int>()
    val loginSuccess: LiveData<Int> = _loginSuccess

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading



    fun login(nom: String, password: String) {
        if (nom.isBlank() || password.isBlank()) {
            return
        }

        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = ItemAPI.API().login(LoginRequest(nom, password))

                if (response.isSuccessful) {
                    val usuari = response.body()
                    if (usuari != null) {
                        _loginSuccess.value = usuari.id
                    } else {
                    }
                } else {
                }
            } catch (e: Exception) {
            } finally {
                _isLoading.value = false
            }
        }
    }
}