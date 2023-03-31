package com.wyb.wyb_android.data.request

import com.google.gson.annotations.SerializedName

data class DiscomfortTitleRequest(
    @SerializedName("myInconvenienceId")
    val discomfortId: Int,
    @SerializedName("inconvenienceString")
    val discomfortTitle: String,
)