package com.wyb.wyb_android.data.response

import com.google.gson.annotations.SerializedName
import com.wyb.wyb_android.data.model.Comfort
import com.wyb.wyb_android.data.model.Discomfort
import com.wyb.wyb_android.data.model.User

data class UserHomeResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data,
)

data class Data(
    val isFollowing: Boolean,
    @SerializedName("user")
    val userData: User,
    @SerializedName("myChallenge")
    val comfort: Comfort?,
    @SerializedName("myInconveniences")
    val discomfortList: List<Discomfort>?,
)