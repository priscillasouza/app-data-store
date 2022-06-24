package com.example.datastore

import android.app.Application

class ApplicationDataStore: Application() {

    override fun onCreate() {
        super.onCreate()
        DataStoreManager.init(this)
    }
}