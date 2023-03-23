package com.wyb.wyb_android.data.model

import com.google.gson.annotations.SerializedName

data class Comfort(
    @SerializedName("id")
    val challengeId: Int,
    val userId: Int,
    @SerializedName("name")
    val comfortTitle: String,
    val isDeleted: Boolean,
    val isFinished: Boolean,
    val createdAt: String,
    val startedAt: String,
    val updatedAt: String,
)