package com.wyb.wyb_android.network

import com.wyb.wyb_android.data.request.DiscomfortFinishRequest
import com.wyb.wyb_android.data.response.BaseResponse
import com.wyb.wyb_android.data.response.HomeResponse
import com.wyb.wyb_android.data.response.UserHomeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface ChallengeService {
    @GET("my-challenge/main")
    suspend fun getHome(): HomeResponse

    @PUT("my-inconvenience/finish")
    suspend fun postDiscomfortFinish(
        @Body body: DiscomfortFinishRequest
    ): BaseResponse

    @GET("my-challenge/user")
    suspend fun getUserHome(
        @Query("userId") userId: Int
    ): UserHomeResponse
}