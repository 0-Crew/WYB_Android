package com.wyb.wyb_android.network

import com.wyb.wyb_android.data.response.BaseResponse
import com.wyb.wyb_android.data.response.SettingResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @GET("user/setting")
    suspend fun getUserInfo(): SettingResponse

    @POST("notification/button")
    suspend fun postFollow(
        @Query("type") type: String,
        @Query("receiverUserId") receiverUserId: Int,
    ): BaseResponse
}