package com.wyb.wyb_android.ui.calendar

import android.content.Context
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.wyb.wyb_android.R

class DisableDateDecorator(private val context: Context) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay?): Boolean = true

    override fun decorate(view: DayViewFacade?) {
        view?.setDaysDisabled(true)
        view?.addSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.gray_4)))
    }
}