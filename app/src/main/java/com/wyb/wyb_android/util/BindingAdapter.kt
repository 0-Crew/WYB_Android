package com.wyb.wyb_android.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisibleOrGone")
fun View.bindVisibleOrGone(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter("isVisibleOrInvisible")
fun View.bindVisibleOrInvisible(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}