package com.wyb.wyb_android.network

import com.wyb.wyb_android.data.request.NicknameRequest
import com.wyb.wyb_android.data.response.BaseResponse
import com.wyb.wyb_android.data.response.ExposureResponse
import com.wyb.wyb_android.data.response.SettingResponse
import retrofit2.http.*

interface UserService {
    @GET("user/setting")
    suspend fun getUserInfo(): SettingResponse

    @POST("user/name")
    suspend fun postNickname(
        @Body body: NicknameRequest
    ): BaseResponse

    @GET("user/private")
    suspend fun getUserExposure(): ExposureResponse

    @PUT("user/private")
    suspend fun updateUserExposure(): ExposureResponse

    @POST("notification/button")
    suspend fun postFollow(
        @Query("type") type: String,
        @Query("receiverUserId") receiverUserId: Int,
    ): BaseResponse
}