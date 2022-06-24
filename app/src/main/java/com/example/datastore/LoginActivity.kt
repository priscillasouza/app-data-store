package com.example.datastore

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.datastore.databinding.ActivityLoginBinding
import com.example.datastore.databinding.ActivityRegisterUserBinding
import kotlinx.coroutines.runBlocking

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var register: ActivityRegisterUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterUserActivity::class.java)
            startActivity(intent)
        }
        setUpListener()
    }

    private fun setUpListener() {
        binding.buttonLogin.setOnClickListener {
            runBlocking {
                saveUser()
                validateLoginWithRegister()
            }
        }
    }

    private fun saveUser() {
        runBlocking {
            DataStoreManager.setEmailUserDataStore(
                binding.editTextEmailLogin.text.toString()
            )
            DataStoreManager.setPasswordUserDataStore(
                binding.editTextPasswordLogin.text.toString()
            )
        }
    }

    private fun validateLoginWithRegister() {
        val emailLogin = binding.editTextEmailLogin.toString()
        val passwordLogin = binding.editTextPasswordLogin.toString()
        val emailRegister = register.editTextEmailRegisterUser.toString()
        val passwordRegister = register.editTextPasswordRegisterUser.toString()

        if (emailLogin == emailRegister && passwordLogin == passwordRegister) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                "Não foi possível validar email e senha, tente outra vez",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}