package com.wyb.wyb_android.ui.calendar

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class CalendarViewModel : ViewModel() {
    private fun getLocalDate(isoDate: String): LocalDate? {
        // TODO: 서버 연동 후 timeZone Korea 로 변경 및 date format 변경
        val sdf = SimpleDateFormat(ISO_DATE_FORMAT, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.time = sdf.parse(isoDate) ?: return null
        calendar.timeZone = TimeZone.getTimeZone("Etc/UTC")
        return LocalDate.of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    companion object {
        private const val ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    }
}