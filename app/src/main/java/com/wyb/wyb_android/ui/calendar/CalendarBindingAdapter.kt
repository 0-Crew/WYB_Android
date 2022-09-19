package com.wyb.wyb_android.ui.calendar

import android.graphics.Paint
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.wyb.wyb_android.R
import com.wyb.wyb_android.util.Utils
import java.time.LocalDate

@BindingAdapter("bottleSrc")
fun setBottleSrc(imageView: ImageView, successItemSize: Int) {
    when (successItemSize) {
        0 -> imageView.setImageResource(R.drawable.ic_bottle_calendar_0)
        1 -> imageView.setImageResource(R.drawable.ic_bottle_calendar_1)
        2 -> imageView.setImageResource(R.drawable.ic_bottle_calendar_2)
        3 -> imageView.setImageResource(R.drawable.ic_bottle_calendar_3)
        4 -> imageView.setImageResource(R.drawable.ic_bottle_calendar_4)
        5 -> imageView.setImageResource(R.drawable.ic_bottle_calendar_5)
        6 -> imageView.setImageResource(R.drawable.ic_bottle_calendar_6)
        else -> imageView.setImageResource(R.drawable.ic_bottle_calendar_7)
    }
}

@BindingAdapter("comfortBackgroundColor")
fun setComfortBackgroundColor(layout: LinearLayout, index: Int) {
    when (index) {
        1 -> layout.setBackgroundColor(layout.context.resources.getColor(R.color.yellow_cal, null))
        2 -> layout.setBackgroundColor(layout.context.resources.getColor(R.color.green_cal, null))
        3 -> layout.setBackgroundColor(layout.context.resources.getColor(R.color.red_cal, null))
        4 -> layout.setBackgroundColor(layout.context.resources.getColor(R.color.blue_cal, null))
        5 -> layout.setBackgroundColor(layout.context.resources.getColor(R.color.purple_cal, null))
        6 -> layout.setBackgroundColor(layout.context.resources.getColor(R.color.orange, null))
        else -> layout.setBackgroundColor(layout.context.resources.getColor(R.color.pink_cal, null))
    }
}

@BindingAdapter("dateTextColor")
fun setDateTextColor(textView: TextView, index: Int) {
    when (index) {
        1 -> textView.setTextColor(textView.context.resources.getColor(R.color.yellow_cal, null))
        2 -> textView.setTextColor(textView.context.resources.getColor(R.color.green_cal, null))
        3 -> textView.setTextColor(textView.context.resources.getColor(R.color.red_cal, null))
        4 -> textView.setTextColor(textView.context.resources.getColor(R.color.blue_cal, null))
        5 -> textView.setTextColor(textView.context.resources.getColor(R.color.purple_cal, null))
        6 -> textView.setTextColor(textView.context.resources.getColor(R.color.orange, null))
        else -> textView.setTextColor(textView.context.resources.getColor(R.color.pink_cal, null))
    }
}

@BindingAdapter("challengeTypeTextColor")
fun setChallengeTypeTextColor(textView: TextView, index: Int) {
    when (index) {
        1 -> textView.setTextColor(textView.context.resources.getColor(R.color.yellow_alpha_50, null))
        2 -> textView.setTextColor(textView.context.resources.getColor(R.color.green_alpha_50, null))
        3 -> textView.setTextColor(textView.context.resources.getColor(R.color.red_alpha_50, null))
        4 -> textView.setTextColor(textView.context.resources.getColor(R.color.blue_alpha_50, null))
        5 -> textView.setTextColor(textView.context.resources.getColor(R.color.purple_alpha_50, null))
        6 -> textView.setTextColor(textView.context.resources.getColor(R.color.orange_alpha_50, null))
        else -> textView.setTextColor(textView.context.resources.getColor(R.color.pink_alpha_50, null))
    }
}

@BindingAdapter("createdDate", "day")
fun setEnabledBeforeToday(checkBox: CheckBox, createdDate: String, day: Int) {
    val createdLocalDate = Utils.convertIsoStringToLocalDate(createdDate) ?: return
    checkBox.isEnabled = !createdLocalDate.plusDays((day - 1).toLong()).isAfter(LocalDate.now())
}

@BindingAdapter("isUnfinished")
fun setUnfinishedTextStyle(textView: TextView, isFinished: Boolean) {
    if (!isFinished) {
        textView.setTextColor(textView.context.resources.getColor(R.color.gray_2, null))
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.setTextColor(textView.context.resources.getColor(R.color.light_gray_3, null))
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}