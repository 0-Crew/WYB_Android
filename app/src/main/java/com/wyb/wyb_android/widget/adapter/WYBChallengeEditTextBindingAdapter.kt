package com.wyb.wyb_android.widget.adapter

import androidx.databinding.BindingAdapter
import com.wyb.wyb_android.R
import com.wyb.wyb_android.widget.WYBChallengeEditText

@BindingAdapter("setChallengeBackgroundStroke")
fun setChallengeBackgroundStroke(view: WYBChallengeEditText, color: Int) {
    val gray = view.resources.getInteger(R.integer.wyb_challenge_edit_text_bg_stroke_gray)
    val orange = view.resources.getInteger(R.integer.wyb_challenge_edit_text_bg_stroke_orange)
    when (color) {
        gray -> view.setChallengeToday(gray)
        orange -> view.setChallengeToday(orange)
    }
}