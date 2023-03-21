package com.wyb.wyb_android.ui.bottleworld

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.wyb.wyb_android.R

@BindingAdapter("setBottleWorldSrc")
fun bindBottleImg(imageView: ImageView, levelOfJuice: String?) {
    when (levelOfJuice?.toInt()) {
        0 -> imageView.setImageResource(R.drawable.ic_bottle_world_0)
        1 -> imageView.setImageResource(R.drawable.ic_bottle_world_1)
        2 -> imageView.setImageResource(R.drawable.ic_bottle_world_2)
        3 -> imageView.setImageResource(R.drawable.ic_bottle_world_3)
        4 -> imageView.setImageResource(R.drawable.ic_bottle_world_4)
        5 -> imageView.setImageResource(R.drawable.ic_bottle_world_5)
        6 -> imageView.setImageResource(R.drawable.ic_bottle_world_6)
        7 -> imageView.setImageResource(R.drawable.ic_bottle_world_7)
        else -> imageView.setImageResource(R.drawable.ic_bottle_world_7)
    }
}