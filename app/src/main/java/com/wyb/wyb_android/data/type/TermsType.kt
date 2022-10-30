package com.wyb.wyb_android.data.type

import com.wyb.wyb_android.R

enum class TermsType(
    val title: Int,
    val url: Int
) {
    PRIVACY_POLICY(
        title = R.string.setting_privacy_policy,
        url = R.string.wyb_privacy_policy
    ),
    SERVICE_TERMS(
        title = R.string.setting_service_terms,
        url = R.string.wyb_service_terms
    )
}