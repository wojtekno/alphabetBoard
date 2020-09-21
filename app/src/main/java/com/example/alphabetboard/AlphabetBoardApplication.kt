package com.example.alphabetboard

import android.app.Application
import com.example.alphabetboard.log.MDebugTree
import timber.log.Timber

class AlphabetBoardApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(MDebugTree())
    }
}