package com.wyb.wyb_android.data.model

data class ChallengeDiscomfort(
    val discomfortId: Int,
    val discomfortTitle: String,
    val startedAt: String,
    val isFinished: Boolean,
    val isToday: Boolean,
    val isFuture: Boolean,
    val day: Int,
    val waterDropDate: String,
)