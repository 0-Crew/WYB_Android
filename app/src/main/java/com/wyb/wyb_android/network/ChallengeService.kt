package com.wyb.wyb_android.network

import com.wyb.wyb_android.data.response.UserHomeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ChallengeService {
    @GET("my-challenge/user")
    suspend fun getUserHome(
        @Query("userId") userId: Int
    ): UserHomeResponse
}