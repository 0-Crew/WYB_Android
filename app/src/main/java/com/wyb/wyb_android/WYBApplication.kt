package com.wyb.wyb_android

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.wyb.wyb_android.data.SharedPreferenceController
import com.wyb.wyb_android.util.Utils

class WYBApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.initialize(this)
        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)
        SharedPreferenceController.init(this)
    }
}