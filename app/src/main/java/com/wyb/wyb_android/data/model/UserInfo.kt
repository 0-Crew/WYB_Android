package com.wyb.wyb_android.data.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id")
    val userId: Int,
    @SerializedName("name")
    val nickname: String,
    @SerializedName("isPrivate")
    val isPrivate: Boolean,
)