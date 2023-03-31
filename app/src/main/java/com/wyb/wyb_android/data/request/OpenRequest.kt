package com.wyb.wyb_android.data.request

import com.google.gson.annotations.SerializedName

data class OpenRequest(
    @SerializedName("convenienceString")
    val comfort: String,
    @SerializedName("inconvenienceString")
    val discomfort: String,
    @SerializedName("isfromToday")
    val selectedToday: Boolean,
)