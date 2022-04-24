package com.wyb.wyb_android.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.getSystemService
import kotlin.math.roundToInt

fun convertDpToPx(context: Context, dp: Int): Int {
    val density = context.resources.displayMetrics.density
    return (dp.toFloat() * density).roundToInt()
}

fun clearFocus(activity: Activity?) {
    val inputMethodManager = activity?.getSystemService<InputMethodManager>()!!
    activity.currentFocus?.let { view ->
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }
}

fun requestFocus(context: Context?, view: View) {
    val inputMethodManager = context?.getSystemService<InputMethodManager>()!!
    view.requestFocus()
    if (view !is EditText) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    } else {
        inputMethodManager.showSoftInput(view, 0)
    }
}