package com.ricardo.tcg_dex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TCGDexApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
