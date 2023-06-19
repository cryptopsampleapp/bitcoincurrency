package com.aldi.coin

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AldiCoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i(this.javaClass.simpleName, "Application initializing..." )
    }
}