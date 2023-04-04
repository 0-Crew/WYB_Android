package com.wyb.wyb_android.data

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceController {
    private const val STORAGE_KEY = "auth"
    private const val TOKEN = "TOKEN"
    private const val NICKNAME = "NICKNAME"

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

    fun setToken(token: String) {
        sharedPreferences.edit()
            .putString(TOKEN, token)
            .apply()
    }

    fun getNickname(): String {
        return sharedPreferences.getString(NICKNAME, "").orEmpty()
    }

    fun setNickname(nickname: String) {
        sharedPreferences.edit()
            .putString(NICKNAME, nickname)
            .apply()
    }

    fun clearAuth() {
        sharedPreferences.edit().clear().apply()
    }
}