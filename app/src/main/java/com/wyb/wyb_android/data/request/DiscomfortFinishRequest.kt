package com.wyb.wyb_android.data.request

import com.google.gson.annotations.SerializedName

data class DiscomfortFinishRequest(
    @SerializedName("myInconvenienceId")
    val discomfortId: Int,
)