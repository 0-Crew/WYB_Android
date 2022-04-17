package com.wyb.wyb_android.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.wyb.wyb_android.R
import com.wyb.wyb_android.databinding.ViewWybPopupWindowBinding
import com.wyb.wyb_android.widget.WYBPopupWindowItemAdapter
import com.wyb.wyb_android.widget.WYBPopupWindowItemAdapter.Companion.TYPE_POPUP_DEFAULT
import com.wyb.wyb_android.widget.WYBPopupWindowItemAdapter.Companion.TYPE_POPUP_SMALL

fun View.showPopupWindowDefault(
    context: Context
) {
    val popupView = ViewWybPopupWindowBinding.inflate(LayoutInflater.from(context))
    initPopupView(popupView, TYPE_POPUP_DEFAULT, context)

    val popupHeight: Int = resources.getInteger(R.integer.wyb_popup_window_height_default)
    val popupMargin: Int = resources.getInteger(R.integer.wyb_popup_window_margin_default)

    val popup = PopupWindow(
        popupView.root,
        this.width,
        convertDpToPx(context, popupHeight),
        false
    )

    popup.setBackgroundDrawable(
        ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.shape_orange_stroke,
            null
        )
    )
    popup.showAsDropDown(this, 0, convertDpToPx(context, popupMargin))
}

fun View.showPopupWindowSmall(
    backgroundRes: Int,
    context: Context,
    margin: Int
) {
    val popupView = ViewWybPopupWindowBinding.inflate(LayoutInflater.from(context))
    initPopupView(popupView, TYPE_POPUP_SMALL, context)

    val popupHeight: Int = resources.getInteger(R.integer.wyb_popup_window_height_small)

    val popup = PopupWindow(
        popupView.root,
        this.width,
        convertDpToPx(context, popupHeight),
        true
    )

    popup.setBackgroundDrawable(
        ResourcesCompat.getDrawable(
            context.resources,
            backgroundRes,
            null
        )
    )
    popup.showAsDropDown(this, 0, convertDpToPx(context, margin))
}

private fun initPopupView(popupWindow: ViewWybPopupWindowBinding, type: Int, context: Context) {
    val wybPopupWindowItemAdapter = WYBPopupWindowItemAdapter(type)
    popupWindow.rvItem.adapter = wybPopupWindowItemAdapter
    popupWindow.rvItem.layoutManager = LinearLayoutManager(context)
}
