package com.wyb.wyb_android.ui.calendar

import android.content.Context
import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.wyb.wyb_android.R

class TodayDecorator(
    private val context: Context,
    private val textColor: Int,
    private val underBarColor: Int,
    private val hasBackground: Boolean
) : DayViewDecorator {
    private val date = CalendarDay.today()

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date) ?: false
    }

    override fun decorate(view: DayViewFacade?) {
        if (view == null) return

        view.apply {
            addSpan(StyleSpan(Typeface.BOLD))
            addSpan(ForegroundColorSpan(textColor))
            addSpan(UnderBarSpan(underBarColor))
        }

        if (hasBackground) {
            val backgroundDrawable =
                ContextCompat.getDrawable(context, R.drawable.inset_calendar_today_background)
                    ?: return
            view.setSelectionDrawable(backgroundDrawable)
        }
    }
}