package com.wyb.wyb_android.data

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceController {
    private const val STORAGE_KEY = "auth"
    private const val TOKEN = "TOKEN"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
    }

    fun getToken(): String {
        return sharedPreferences.getString(TOKEN, "").orEmpty()
    }

    fun setToken(jwt: String) {
        sharedPreferences.edit()
            .putString(TOKEN, jwt)
            .apply()
    }

    fun clearToken() {
        sharedPreferences.edit().clear().apply()
    }
}