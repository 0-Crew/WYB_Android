package com.wyb.wyb_android.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisibleOrGone")
fun View.bindIsVisibleOrGone(isVisibility: Boolean) {
    if (isVisibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter("isVisibleOrInvisible")
fun View.bindIsVisibleOrInvisible(isVisibility: Boolean) {
    if (isVisibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}