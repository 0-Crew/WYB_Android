package com.wyb.wyb_android.data.response

import com.google.gson.annotations.SerializedName
import com.wyb.wyb_android.data.model.UserInfo

data class SettingResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: SettingData,
)

data class SettingData(
    @SerializedName("user")
    val userData: UserInfo,
)