package com.example.retrofit_4

import android.app.Application
import com.example.retrofit_4.data.AppContainer
import com.example.retrofit_4.data.DefaultAppContainer

class ApplicationContainer : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}