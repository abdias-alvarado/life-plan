package com.example.abdiasalvarado.lifeplan

import android.app.Application
import com.facebook.appevents.AppEventsLogger
import com.facebook.FacebookSdk



class LifePlanApp: Application(){


    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }
}