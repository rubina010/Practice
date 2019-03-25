package com.example.letspractice

import android.app.Application
import com.facebook.stetho.Stetho

class LetspracticeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}