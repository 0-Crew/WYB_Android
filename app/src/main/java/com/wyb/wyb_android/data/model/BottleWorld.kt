package com.wyb.wyb_android.data.model

import com.google.gson.annotations.SerializedName

data class BottleWorld(
    @SerializedName("challenge")
    val challengeData: BottleWorldChallenge? = null,
    @SerializedName("user")
    val userData: BottleWorldUser,
    @SerializedName("follow")
    val isFollow: Boolean,
)

data class BottleWorldChallenge(
    @SerializedName("count")
    val waterLevel: String,
    @SerializedName("id")
    val challengeId: Int,
    @SerializedName("name")
    val challengeName: String,
    val startedAt: String,
    val userId: Int
)

data class BottleWorldUser(
    @SerializedName("id")
    val userId: Int,
    @SerializedName("name")
    val nickname: String
)