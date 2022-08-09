package com.wyb.wyb_android.ui.calendar

import android.content.Context
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.wyb.wyb_android.R

class DateDecorator(context: Context) : DayViewDecorator {
    private val backgroundDrawable =
        ContextCompat.getDrawable(context, R.drawable.inset_calendar_date_background)

    override fun shouldDecorate(day: CalendarDay?): Boolean = true

    override fun decorate(view: DayViewFacade?) {
        if (backgroundDrawable != null) {
            view?.setSelectionDrawable(backgroundDrawable)
        }
    }
}