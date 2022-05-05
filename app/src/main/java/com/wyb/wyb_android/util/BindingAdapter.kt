package com.wyb.wyb_android.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisibleOrGone")
fun View.bindVisibleOrGone(isVisibility: Boolean) {
    if (isVisibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter("isVisibleOrInvisible")
fun View.bindVisibleOrInvisible(isVisibility: Boolean) {
    if (isVisibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}