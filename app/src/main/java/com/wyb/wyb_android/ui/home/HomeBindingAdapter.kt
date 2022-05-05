package com.wyb.wyb_android.ui.home

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.wyb.wyb_android.R

@BindingAdapter("setBottleSrc")
fun bindBottleImg(imageView: ImageView, levelOfJuice: Int) {
    when (levelOfJuice) {
        7 -> imageView.setImageResource(R.drawable.ic_bottle_main_0)
        6 -> imageView.setImageResource(R.drawable.ic_bottle_main_1)
        5 -> imageView.setImageResource(R.drawable.ic_bottle_main_2)
        4 -> imageView.setImageResource(R.drawable.ic_bottle_main_3)
        3 -> imageView.setImageResource(R.drawable.ic_bottle_main_4)
        2 -> imageView.setImageResource(R.drawable.ic_bottle_main_5)
        1 -> imageView.setImageResource(R.drawable.ic_bottle_main_6)
        0 -> imageView.setImageResource(R.drawable.ic_bottle_main_7)
        else -> imageView.setImageResource(R.drawable.ic_bottle_main_0)
    }
}

@BindingAdapter("setOtherProfileText")
fun bindFollowingProfile(textView: TextView, nickName: String) {
    textView.text = nickName.substring(0, 1)
}