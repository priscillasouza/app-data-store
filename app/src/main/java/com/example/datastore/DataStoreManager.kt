package com.example.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object DataStoreManager {

    //variável com o contexto global do app
    private lateinit var application: Application

    //inicializa o contexto
    fun init(application: Application) {
        this.application
    }

    //criando extension para acessar a instância
    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore("USER_INFO")

}