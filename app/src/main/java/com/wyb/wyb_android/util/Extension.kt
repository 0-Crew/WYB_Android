package com.wyb.wyb_android.util

import android.content.Context
import kotlin.math.roundToInt

fun convertDpToPx(context: Context, dp: Int): Int {
    val density = context.resources.displayMetrics.density
    return (dp.toFloat() * density).roundToInt()
}