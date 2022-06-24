package com.example.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

object DataStoreManager {

    //variável com o contexto global do app
    private lateinit var application: Application

    //inicializa o contexto
    fun init(application: Application) {
        this.application = application
    }

    //criando extension para acessar a instância do DataStore
    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "USER_INFO")
    private val Context.preferencesRegisterDataStore: DataStore<Preferences> by preferencesDataStore(name = "REGISTER_USER-INFO")

    //Registro do usuário
    suspend fun setEmailRegisterUserDataStore(emailRegister: String) {
        application.preferencesRegisterDataStore.edit { preferences ->
            preferences[stringPreferencesKey("EMAIL_REGISTER")] = emailRegister
        }
    }

    suspend fun setPasswordRegisterUserDataStore(passwordRegister: String) {
        application.preferencesRegisterDataStore.edit { preferences ->
            preferences[stringPreferencesKey("PASSWORD_REGISTER")] = passwordRegister
        }
    }

    suspend fun readEmailRegisterUserDataStore(): String {
        return application.preferencesRegisterDataStore.data.first()[stringPreferencesKey("EMAIL_REGISTER")] ?: ""
    }

    suspend fun readPasswordRegisterUserDataStore(): String {
        return application.preferencesRegisterDataStore.data.first()[stringPreferencesKey("PASSWORD_REGISTER")] ?: ""
    }

    //Login do usuário
    suspend fun setEmailUserDataStore(email: String) {
        application.preferencesDataStore.edit { preferences ->
            preferences[stringPreferencesKey("EMAIL")] = email
        }
    }

    suspend fun setPasswordUserDataStore(password: String) {
        application.preferencesDataStore.edit { preferences ->
            preferences[stringPreferencesKey("PASSWORD")] = password
        }
    }

    //funções de leitura das chaves name e age
    suspend fun readEmailUserDataStore(): String {
        return application.preferencesDataStore.data.first()[stringPreferencesKey("EMAIL")] ?: ""
    }

    suspend fun readPasswordUserDataStore(): String {
        return application.preferencesDataStore.data.first()[stringPreferencesKey("PASSWORD")] ?: ""
    }
}