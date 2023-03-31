package com.wyb.wyb_android.widget.adapter

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.wyb.wyb_android.R
import com.wyb.wyb_android.widget.WYBChallengeEditText
import kotlinx.android.synthetic.main.view_wyb_challenge_edit_text.view.*
import kotlinx.android.synthetic.main.view_wyb_label_edit_text.view.*

@BindingAdapter("setChallengeBackgroundStroke")
fun setChallengeBackgroundStroke(view: WYBChallengeEditText, color: Int) {
    val gray = view.resources.getInteger(R.integer.wyb_challenge_edit_text_bg_stroke_gray)
    val orange = view.resources.getInteger(R.integer.wyb_challenge_edit_text_bg_stroke_orange)
    when (color) {
        gray -> view.setChallengeToday(gray)
        orange -> view.setChallengeToday(orange)
    }
}

@BindingAdapter("inputDiscomfortTextAttrChanged")
fun setWYBChallengeEditTextInverseBindingListener(
    view: WYBChallengeEditText,
    listener: InverseBindingListener?
) {
    view.etDiscomfort.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            listener?.onChange()
        }
    })
}

@InverseBindingAdapter(attribute = "inputDiscomfortText", event = "inputDiscomfortTextAttrChanged")
fun getDiscomfortInputText(view: WYBChallengeEditText): String {
    return view.etDiscomfort.text.toString()
}