package com.wyb.wyb_android.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.getSystemService
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.wyb.wyb_android.R
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

    private fun formatDate(date: Int): String {
        return when (date) {
            1, 2, 3, 4, 5, 6, 7, 8, 9 -> "0${date}"
            else -> "$date"
        }
    }

    fun setDateText(firstDate: CalendarDay, lastDate: CalendarDay) : String {
        val startMonth = formatDate(firstDate.month)
        val startDay = formatDate(firstDate.day)
        val endMonth = formatDate(lastDate.month)
        val endDay = formatDate(lastDate.day)
        val resources = applicationContext.resources

        return if (startMonth == endMonth) {
            String.format(resources.getString(R.string.date_format), startMonth, startDay, endDay)
        } else {
            String.format(resources.getString(R.string.date_format_long), startMonth, startDay, endMonth, endDay)
        }
    }

    fun setDateText(firstDate: LocalDate, lastDate: LocalDate) : String {
        val startMonth = formatDate(firstDate.monthValue)
        val startDay = formatDate(firstDate.dayOfMonth)
        val endMonth = formatDate(lastDate.monthValue)
        val endDay = formatDate(lastDate.dayOfMonth)
        val resources = applicationContext.resources

        return if (startMonth == endMonth) {
            String.format(resources.getString(R.string.date_format), startMonth, startDay, endDay)
        } else {
            String.format(resources.getString(R.string.date_format_long), startMonth, startDay, endMonth, endDay)
        }
    }
}

fun hideKeyboard(activity: Activity?, view: View) {
    val inputMethodManager = activity?.getSystemService<InputMethodManager>() ?: return
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}