package com.wyb.wyb_android.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.getSystemService
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.math.roundToInt

object Utils : BaseUtil() {
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

    fun convertIsoStringToLocalDate(isoDate: String): LocalDate? {
        // TODO: 서버 연동 후 timeZone Korea 로 변경
        val sdf = SimpleDateFormat(Constants.ISO_DATE_FORMAT, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.time = sdf.parse(isoDate) ?: return null
        calendar.timeZone = TimeZone.getTimeZone("Etc/UTC")
        return LocalDate.of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
}

fun hideKeyboard(activity: Activity?, view: View) {
    val inputMethodManager = activity?.getSystemService<InputMethodManager>() ?: return
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}