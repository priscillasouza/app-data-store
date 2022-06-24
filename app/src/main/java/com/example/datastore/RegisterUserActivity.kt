package com.example.datastore

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.datastore.databinding.ActivityRegisterUserBinding
import kotlinx.coroutines.runBlocking

class RegisterUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListener()

        setSupportActionBar(binding.toolbarRegisterUser)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpListener() {
        binding.buttonRegisterUser.setOnClickListener {
            Toast.makeText(this, "Usuátrio registrado com sucesso", Toast.LENGTH_SHORT).show()
            saveRegisterUser()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveRegisterUser() {
        runBlocking {
            DataStoreManager.setEmailRegisterUserDataStore(
                binding.editTextEmailRegisterUser.text.toString()
            )
            DataStoreManager.setPasswordRegisterUserDataStore(
                binding.editTextPasswordRegisterUser.text.toString()
            )
        }
    }
}