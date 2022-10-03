package com.wyb.wyb_android.ui.calendar

import android.content.Context
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.wyb.wyb_android.R

class DateDecorator(private val context: Context) : DayViewDecorator {
    private val currentMonthDates = ArrayList<CalendarDay>()

    override fun shouldDecorate(day: CalendarDay?): Boolean = currentMonthDates.contains(day)

    override fun decorate(view: DayViewFacade?) {
        view?.setDaysDisabled(true)
        view?.addSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.gray_3)))
    }

    fun updateCurrentMonthDates(dates: ArrayList<CalendarDay>) {
        currentMonthDates.clear()
        currentMonthDates.addAll(dates)
    }
}