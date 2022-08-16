package com.wyb.wyb_android.ui.calendar

import android.content.Context
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.wyb.wyb_android.R

class EventDecorator(
    private val context: Context,
    private val dates: HashSet<CalendarDay>,
    private val drawable: Int
) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay?): Boolean = dates.contains(day)

    override fun decorate(view: DayViewFacade?) {
        val rangeDrawable = ContextCompat.getDrawable(context, drawable) ?: return
        view?.setSelectionDrawable(rangeDrawable)
        view?.addSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.white)))
    }
}