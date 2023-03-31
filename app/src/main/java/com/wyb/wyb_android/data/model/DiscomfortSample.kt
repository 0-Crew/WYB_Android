package com.wyb.wyb_android.data.model

import com.google.gson.annotations.SerializedName

data class DiscomfortSample(
    @SerializedName("id")
    val disComfortId: Int,
    @SerializedName("name")
    val discomfortTitle: String,
    val isDeleted: Boolean,
    val createdAt: String,
    val updatedAt: String,
)