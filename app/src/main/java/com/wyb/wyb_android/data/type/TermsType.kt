package com.wyb.wyb_android.data.type

import com.wyb.wyb_android.R

enum class TermsType(
    val title: Int,
    val url: String
) {
    PRIVACY_POLICY(
        title = R.string.setting_privacy_policy,
        url = "file:///android_res/raw/wyb_privacy_policy.html"
    ),
    SERVICE_TERMS(
        title = R.string.setting_service_terms,
        url = "file:///android_res/raw/wyb_service_terms.html"
    )
}