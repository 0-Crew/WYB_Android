package com.wyb.wyb_android.data.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: AuthData?
)

data class AuthData(
    val type: String,
    @SerializedName("accesstoken")
    val accessToken: String,
    @SerializedName("refreshtoken")
    val refreshToken: String,
)