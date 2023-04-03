package com.wyb.wyb_android.data.type

enum class AuthType {
    LOGIN,
    SIGNUP;

    override fun toString(): String {
        return when (this) {
            LOGIN -> "login"
            SIGNUP -> "signUp"
        }
    }
}