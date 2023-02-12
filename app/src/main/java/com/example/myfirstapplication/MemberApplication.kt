package com.example.myfirstapplication

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.myfirstapplication.data.AppDatabase

// date: 11 Oct 2022


// the application context - reference to Context object that is needed by many system services.
class MemberApplication: Application()  {
    val database: AppDatabase by lazy { AppDatabase.getInstance() }
    companion object{
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("QQQ","MyApp onCreate()")
        appContext = applicationContext
    }
}