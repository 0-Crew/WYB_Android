package com.wyb.wyb_android.widget

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.wyb.wyb_android.R
import com.wyb.wyb_android.databinding.ViewWybToastBinding
import com.wyb.wyb_android.util.Utils

object WYBToast {
    fun createToast(context: Context, message: String): Toast {
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<ViewWybToastBinding>(
            inflater,
            R.layout.view_wyb_toast,
            null,
            false
        )
        binding.tvToast.text = message

        return Toast(context).apply {
            setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, Utils.convertDpToPx(60))
            duration = Toast.LENGTH_SHORT
            view = binding.root
        }
    }
}