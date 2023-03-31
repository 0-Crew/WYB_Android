package com.wyb.wyb_android.ui.home

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.wyb.wyb_android.R

@BindingAdapter("setBottleSrc")
fun bindBottleImg(imageView: ImageView, levelOfJuice: Int) {
    when (levelOfJuice) {
        0 -> imageView.setImageResource(R.drawable.ic_bottle_main_0)
        1 -> imageView.setImageResource(R.drawable.ic_bottle_main_1)
        2 -> imageView.setImageResource(R.drawable.ic_bottle_main_2)
        3 -> imageView.setImageResource(R.drawable.ic_bottle_main_3)
        4 -> imageView.setImageResource(R.drawable.ic_bottle_main_4)
        5 -> imageView.setImageResource(R.drawable.ic_bottle_main_5)
        6 -> imageView.setImageResource(R.drawable.ic_bottle_main_6)
        7 -> imageView.setImageResource(R.drawable.ic_bottle_main_7)
        else -> imageView.setImageResource(R.drawable.ic_bottle_main_0)
    }
}

@BindingAdapter("setProfileFirstText")
fun bindProfileFirstText(textView: TextView, nickName: String) {
    textView.text = nickName.substring(0, 1)
}