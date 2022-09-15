package com.wyb.wyb_android.ui.calendar

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.style.LineBackgroundSpan
import com.wyb.wyb_android.util.Utils

class UnderBarSpan(private val color: Int) : LineBackgroundSpan {
    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        lineNumber: Int
    ) {
        paint.color = color
        canvas.drawRect(
            Rect(
                (left + right) / 2 - Utils.convertDpToPx(5),
                bottom + Utils.convertDpToPx(2),
                (left + right) / 2 + Utils.convertDpToPx(5),
                bottom + Utils.convertDpToPx(4)
            ), paint
        )
    }
}