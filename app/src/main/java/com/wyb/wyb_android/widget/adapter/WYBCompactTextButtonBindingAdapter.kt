package com.wyb.wyb_android.widget.adapter

import androidx.databinding.BindingAdapter
import com.wyb.wyb_android.R
import com.wyb.wyb_android.widget.WYBCompactTextButton

@BindingAdapter("setCompactBtnBackground")
fun bindCompactBtnBackground(view: WYBCompactTextButton, color: Int) {
    val orange = view.resources.getInteger(R.integer.wyb_compact_text_btn_bg_orange)
    val orangeStroke =
        view.resources.getInteger(R.integer.wyb_compact_text_btn_bg_orange_stroke)
    when (color) {
        orange -> view.setBackground(orange)
        orangeStroke -> view.setBackground(orangeStroke)
    }
}

@BindingAdapter("setCompatBtnFollowText")
fun bindCompatBtnFollowText(view: WYBCompactTextButton, isFollow: Boolean) {
    if (isFollow) {
        view.setBtnTitleText(view.resources.getString(R.string.bottle_world_following))
    } else {
        view.setBtnTitleText(view.resources.getString(R.string.bottle_world_follow))
    }
}