package com.wyb.wyb_android.ui.calendar

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.wyb.wyb_android.R

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