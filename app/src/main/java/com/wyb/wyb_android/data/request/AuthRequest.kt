package com.wyb.wyb_android.data.request

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("idKey")
    val email: String,
    val token: String,
    val provider: String = "kakao"
)