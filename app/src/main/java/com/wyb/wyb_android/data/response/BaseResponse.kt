package com.wyb.wyb_android.data.response

data class BaseResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
)