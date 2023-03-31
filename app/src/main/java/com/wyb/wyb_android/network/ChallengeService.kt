package com.wyb.wyb_android.network

import com.wyb.wyb_android.data.request.DiscomfortFinishRequest
import com.wyb.wyb_android.data.request.DiscomfortTitleRequest
import com.wyb.wyb_android.data.request.OpenRequest
import com.wyb.wyb_android.data.response.BaseResponse
import com.wyb.wyb_android.data.response.HomeResponse
import com.wyb.wyb_android.data.response.UserHomeResponse
import retrofit2.http.*

interface ChallengeService {
    @GET("my-challenge/main")
    suspend fun getHome(): HomeResponse

    @PUT("my-inconvenience/finish")
    suspend fun postDiscomfortFinish(
        @Body body: DiscomfortFinishRequest
    ): BaseResponse

    @PUT("my-inconvenience/update")
    suspend fun updateDiscomfortTitle(
        @Body body: DiscomfortTitleRequest
    ): BaseResponse

    @GET("my-challenge/user")
    suspend fun getUserHome(
        @Query("userId") userId: Int
    ): UserHomeResponse

    @POST("my-challenge/add")
    suspend fun postChallengeOpen(
        @Body body: OpenRequest
    ): BaseResponse
}