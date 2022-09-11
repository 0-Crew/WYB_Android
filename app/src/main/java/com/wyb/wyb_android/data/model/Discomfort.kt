package com.wyb.wyb_android.data.model

data class Discomfort(
    val id: Int,
    val name: String,
    val createdAt: String,
    val updatedAt: String,
    val isDeleted: Boolean,
    val myChallengeId: Int,
    val day: Int,
    val isFinished: Boolean,
    val userId: Int
)