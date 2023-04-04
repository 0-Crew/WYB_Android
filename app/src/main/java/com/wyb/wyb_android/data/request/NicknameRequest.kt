package com.wyb.wyb_android.data.request

import com.google.gson.annotations.SerializedName

data class NicknameRequest(
    @SerializedName("name")
    val nickname: String,
)