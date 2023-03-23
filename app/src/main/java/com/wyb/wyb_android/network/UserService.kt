package com.wyb.wyb_android.network

import com.wyb.wyb_android.data.response.BaseResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("notification/button")
    suspend fun postFollow(
        @Query("type") type: String,
        @Query("receiverUserId") receiverUserId: Int,
    ): BaseResponse
}