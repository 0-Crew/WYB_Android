package com.wyb.wyb_android.util

import android.content.Context

abstract class BaseUtil {
    private lateinit var applicationContext: Context

    internal open fun initialize(context: Context) {
        applicationContext = context.applicationContext
    }
}