package com.wyb.wyb_android

import android.app.Application
import com.wyb.wyb_android.util.Utils

class WYBApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.initialize(this)
    }
}