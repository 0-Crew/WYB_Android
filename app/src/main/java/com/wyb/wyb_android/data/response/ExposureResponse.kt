package com.wyb.wyb_android.data.response

data class ExposureResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ExposureData,
)

data class ExposureData(
    val isPrivate: Boolean,
)