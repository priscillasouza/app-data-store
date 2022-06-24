package com.example.datastore

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarHome)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        show()
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

    private fun show() {
        val email: String
        val password: String
        runBlocking {
            email = DataStoreManager.readEmailUserDataStore()
            password = DataStoreManager.readPasswordUserDataStore()
        }

        Toast.makeText(this, "Nome: $email, Senha: $password", Toast.LENGTH_SHORT).show()
    }
}