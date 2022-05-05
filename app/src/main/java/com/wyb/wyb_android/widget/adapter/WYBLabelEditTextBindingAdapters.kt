package com.wyb.wyb_android.widget.adapter

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.wyb.wyb_android.R
import com.wyb.wyb_android.widget.WYBLabelEditText
import kotlinx.android.synthetic.main.view_wyb_label_edit_text.view.*

@BindingAdapter("inputText")
fun setInputText(view: WYBLabelEditText, inputText: String?) {
    if (inputText.isNullOrBlank()) {
        view.etInput.text.clear()
    } else if (inputText != view.etInput.text.toString()) {
        view.etInput.setText(inputText)
    }
}

@BindingAdapter("inputTextAttrChanged")
fun setWYBLabelEditTextInverseBindingListener(
    view: WYBLabelEditText,
    listener: InverseBindingListener?
) {
    view.etInput.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            listener?.onChange()
        }
    })
}

@InverseBindingAdapter(attribute = "inputText", event = "inputTextAttrChanged")
fun getInputText(view: WYBLabelEditText): String {
    return view.etInput.text.toString()
}

@BindingAdapter("setBackgroundStroke")
fun setBackgroundStroke(view: WYBLabelEditText, color: Int) {
    val gray = view.context.resources.getInteger(R.integer.wyb_label_edit_text_bg_stroke_gray)
    val orange = view.context.resources.getInteger(R.integer.wyb_label_edit_text_bg_stroke_orange)
    when (color) {
        gray -> view.setBackgroundStroke(gray)
        orange -> view.setBackgroundStroke(orange)
    }
}