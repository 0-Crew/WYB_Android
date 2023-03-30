package com.wyb.wyb_android.network

import com.wyb.wyb_android.data.request.FollowRequest
import com.wyb.wyb_android.data.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface BottleWorldService {
    @POST("bottleworld")
    suspend fun postFollow(
        @Body body: FollowRequest
    ): BaseResponse
}