package com.wyb.wyb_android.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val userId: Int,
    @SerializedName("name")
    val nickname: String,
    val email: String,
    val snsId: String,
    val provider: String,
    val refreshToken: String,
    val isDeleted: Boolean,
    val isPrivate: Boolean,
    val idFirebase: String?,
    val authorizationCode: String?,
    val createdAt: String,
    val updatedAt: String,
)