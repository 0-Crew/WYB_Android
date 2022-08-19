package com.wyb.wyb_android.ui.calendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*

class CalendarViewModel : ViewModel() {
    private val localDates = arrayListOf<ArrayList<LocalDate>>()
    val eventDates = listOf(
        "2022-06-06T19:13:14.582Z",
        "2022-06-15T19:13:14.582Z",
        "2022-06-25T19:13:14.582Z",
        "2022-07-06T19:13:14.582Z",
        "2022-07-15T19:13:14.582Z",
        "2022-07-25T19:13:14.582Z",
        "2022-08-03T19:13:14.582Z",
        "2022-08-13T19:13:14.582Z"
    )
    val datesLeft = arrayListOf<ArrayList<CalendarDay>>()
    val datesCenter = arrayListOf<ArrayList<CalendarDay>>()
    val datesRight = arrayListOf<ArrayList<CalendarDay>>()
    val datesIndependent = arrayListOf<ArrayList<CalendarDay>>()
    val hasContainedToday = MutableLiveData<Boolean>()
    val rangeContainsToday = MutableLiveData<Int?>()
    val prevSelectedRange = arrayListOf<CalendarDay>()

    fun setEvent() {
        formatDates()
        setRange()
        updateRangeContainsToday()
    }

    private fun formatDates() {
        for (dateString in eventDates) {
            val localDate = convertIsoStringToLocalDate(dateString)
            if (localDate != null) {
                localDates.add(arrayListOf(localDate))
            }
        }
    }

    private fun setRange() {
        for (localDate in localDates) {
            for (day in 1..6) {
                localDate.add(localDate[0].plusDays(day.toLong()))
            }
        }

        for ((index, localDate) in localDates.withIndex()) {
            initDatesArrayList()

            for (date in localDate) {
                var left = false
                var right = false

                for (currentDate in localDate) {
                    if (date.dayOfWeek == DayOfWeek.SATURDAY) {
                        if (!localDate.toHashSet().contains(currentDate.minusDays(1)) && date.isEqual(currentDate)) {
                            left = false
                            right = false
                            break
                        }
                        left = true
                        continue
                    }
                    if (date.dayOfWeek == DayOfWeek.SUNDAY) {
                        if (!localDate.toHashSet().contains(currentDate.plusDays(1)) && date.isEqual(currentDate)) {
                            left = false
                            right = false
                            break
                        }
                        right = true
                        continue
                    }
                    if (date.isEqual(currentDate.plusDays(1))) {
                        left = true
                    }
                    if (currentDate.isEqual(date.plusDays(1))) {
                        right = true
                    }
                }

                if (left && right) {
                    datesCenter[index].add(CalendarDay.from(date.year, date.monthValue, date.dayOfMonth))
                } else if (left) {
                    datesLeft[index].add(CalendarDay.from(date.year, date.monthValue, date.dayOfMonth))
                } else if (right) {
                    datesRight[index].add(CalendarDay.from(date.year, date.monthValue, date.dayOfMonth))
                } else {
                    datesIndependent[index].add(CalendarDay.from(date.year, date.monthValue, date.dayOfMonth))
                }
            }
        }
    }

    private fun initDatesArrayList() {
        datesLeft.add(arrayListOf())
        datesCenter.add(arrayListOf())
        datesRight.add(arrayListOf())
        datesIndependent.add(arrayListOf())
    }

    private fun updateRangeContainsToday() {
        for ((index, dates) in localDates.withIndex()) {
            if (dates.toHashSet().contains(
                    LocalDate.of(
                        LocalDate.now().year,
                        LocalDate.now().monthValue,
                        LocalDate.now().dayOfMonth
                    )
                )
            ) {
                hasContainedToday.value = true
                rangeContainsToday.value = index
                break
            } else {
                hasContainedToday.value = false
                rangeContainsToday.value = null
            }
        }
    }

    fun getRangeContainsSelectedDate(calDay: CalendarDay): Pair<CalendarDay, CalendarDay>? {
        val date = convertCalendarDayToLocalDate(calDay)
        var range: Pair<CalendarDay, CalendarDay>? = null
        for (dates in localDates) {
            if (dates.toHashSet().contains(date)) {
                val firstDay = convertLocalDateToCalendarDay(dates[0])
                val lastDay = convertLocalDateToCalendarDay(dates[6])
                range = Pair(firstDay, lastDay)
            }
        }
        return range
    }

    private fun convertIsoStringToLocalDate(isoDate: String): LocalDate? {
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

    private fun convertCalendarDayToLocalDate(calDay: CalendarDay): LocalDate? {
        return LocalDate.of(calDay.year, calDay.month, calDay.day)
    }

    private fun convertLocalDateToCalendarDay(localDate: LocalDate): CalendarDay {
        return CalendarDay.from(localDate.year, localDate.monthValue, localDate.dayOfMonth)
    }

    companion object {
        private const val ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    }
}