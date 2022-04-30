package com.wyb.wyb_android.widget

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import kotlinx.android.synthetic.main.view_wyb_label_edit_text.view.*

object WYBLabelEditTextBindingAdapters {
    @BindingAdapter("inputText")
    @JvmStatic
    fun setInputText(view: WYBLabelEditText, inputText: String?) {
        if (!inputText.isNullOrBlank() && inputText != view.etInput.text.toString()) {
            view.etInput.setText(inputText)
        }
    }

    @BindingAdapter("inputTextAttrChanged")
    @JvmStatic
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
    @JvmStatic
    fun getInputText(view: WYBLabelEditText): String {
        return view.etInput.text.toString()
    }
}