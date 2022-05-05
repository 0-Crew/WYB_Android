package com.wyb.wyb_android.data.model

data class Challenge(
    val id: Int,
    val name: String,
    val isFinished: Boolean,
    val isToday: Boolean,
    val isFuture: Boolean,
    val day: Int,
)
