package com.wyb.wyb_android.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.getSystemService
import kotlin.math.roundToInt

object Utils : BaseUtils() {
    private lateinit var inputMethodManager: InputMethodManager
    private lateinit var applicationContext: Context

    override fun initialize(context: Context) {
        super.initialize(context)
        applicationContext = context
        inputMethodManager = checkNotNull(context.getSystemService()) {
            "Application is instantiated before any other class when the process for your application/package is created"

        }
    }

    fun clearFocus(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }

    fun requestFocus(view: View) {
        view.requestFocus()
        if (view !is EditText) {
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        } else {
            inputMethodManager.showSoftInput(view, 0)
        }
    }

    fun convertDpToPx(dp: Int): Int {
        val density = applicationContext.resources.displayMetrics.density
        return (dp.toFloat() * density).roundToInt()
    }
}

fun hideKeyboard(activity: Activity?, view: View) {
    val inputMethodManager = activity?.getSystemService<InputMethodManager>() ?: return
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}