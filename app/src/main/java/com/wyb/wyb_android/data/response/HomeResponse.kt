package com.wyb.wyb_android.data.response

import com.google.gson.annotations.SerializedName
import com.wyb.wyb_android.data.model.Comfort
import com.wyb.wyb_android.data.model.Discomfort
import com.wyb.wyb_android.data.model.DiscomfortSample
import com.wyb.wyb_android.data.model.OtherProfile

data class HomeResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: HomeData,
)

data class HomeData(
    @SerializedName("myFollowings")
    val followingList: List<OtherProfile>,
    @SerializedName("myChallenge")
    val myChallenge: Comfort?,
    @SerializedName("myInconveniences")
    val discomfortList: List<Discomfort>,
    @SerializedName("inconvenience")
    val sampleDiscomfort: List<DiscomfortSample>,
)