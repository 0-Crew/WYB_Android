package com.wyb.wyb_android.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.wyb.wyb_android.databinding.ViewWybPopupWindowBinding
import com.wyb.wyb_android.widget.WYBPopupWindowItemAdapter
import com.wyb.wyb_android.widget.WYBPopupWindowItemAdapter.Companion.TYPE_POPUP_DEFAULT

fun View.showPopupWindow(
    backgroundRes: Int,
    context: Context,
    type: Int
) {
    val popupView = ViewWybPopupWindowBinding.inflate(LayoutInflater.from(context))
    initPopupView(popupView, type, context)

    val popupHeight = when (type) {
        TYPE_POPUP_DEFAULT -> 158
        else -> 134
    }

    val popup = PopupWindow(
        popupView.root,
        this.width,
        convertDpToPx(context, popupHeight),
        false
    )

    popup.setBackgroundDrawable(
        ResourcesCompat.getDrawable(
            context.resources,
            backgroundRes,
            null
        )
    )
    popup.showAsDropDown(this, 0, convertDpToPx(context, 6))
}

private fun initPopupView(popupWindow: ViewWybPopupWindowBinding, type: Int, context: Context) {
    val wybPopupWindowItemAdapter = WYBPopupWindowItemAdapter(type)
    popupWindow.rvItem.adapter = wybPopupWindowItemAdapter
    popupWindow.rvItem.layoutManager = LinearLayoutManager(context)
}
