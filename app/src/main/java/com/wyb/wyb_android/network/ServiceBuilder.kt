package com.wyb.wyb_android.network

import com.wyb.wyb_android.data.SharedPreferenceController
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val BASE_URL = "https://asia-northeast3-washyourbottle.cloudfunctions.net/api/"

    private val headerInterceptor = Interceptor { chain ->
        with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("Authorization", SharedPreferenceController.getToken())
                .build()
            proceed(newRequest)
        }
    }

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(headerInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userService: UserService = retrofit.create(UserService::class.java)
    val challengeService: ChallengeService = retrofit.create(ChallengeService::class.java)
    val bottleWorldService: BottleWorldService = retrofit.create(BottleWorldService::class.java)
}