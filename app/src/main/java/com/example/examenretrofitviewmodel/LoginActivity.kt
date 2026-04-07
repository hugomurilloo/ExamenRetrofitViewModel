package com.example.examenretrofitviewmodel

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class LoginActivity : AppCompatActivity() {

    // Forma recomendada para inicializar ViewModels
    private val viewModel: LoginViewModel by viewModels()

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    private lateinit var ivLogo: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        observeViewModel()
        setupClickListeners()
    }

    private fun initViews() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        ivLogo = findViewById(R.id.ivLogo)

        Glide.with(this)
            .load("https://oracleitic.mooo.com/imatges/inici.jpg")
            .placeholder(R.drawable.ic_lock)
            .error(R.drawable.ic_trash)
            .into(ivLogo)

    }

    private fun observeViewModel() {




        viewModel.loginSuccess.observe(this) { userId ->
            // Verificamos que sea un ID válido (mayor que 0)
            if (userId != null && userId > 0) {
                val intent = Intent(this, ReservesActivity::class.java).apply {
                    putExtra("USER_ID", userId)
                }
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setupClickListeners() {
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

                viewModel.login(email, password)
        }
    }
}