package com.wyb.wyb_android.widget.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.wyb.wyb_android.widget.WYBCompactTextButton

@BindingAdapter("setComfortTitleText")
fun bindComfortTitleText(view: WYBCompactTextButton, title: LiveData<String>) {
    view.setComfortTitleText(title)
}