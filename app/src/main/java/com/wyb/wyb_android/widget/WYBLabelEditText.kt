package com.wyb.wyb_android.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.wyb.wyb_android.R
import com.wyb.wyb_android.databinding.ViewWybLabelEditTextBinding

class WYBLabelEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding: ViewWybLabelEditTextBinding =
        ViewWybLabelEditTextBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
        initializeAttrs(context, attrs)
    }

    var backgroundStroke: Drawable?
        get() = binding.layout.background
        set(value) {
            binding.layout.background = value
        }

    var labelText: String?
        get() = binding.tvLabel.text.toString()
        set(value) {
            binding.tvLabel.text = value
        }

    var showIcon: Boolean
        get() = binding.ivIcon.isVisible
        set(value) {
            binding.ivIcon.isVisible = value
        }

    var iconType: Drawable?
        get() = binding.ivIcon.drawable
        set(value) {
            binding.ivIcon.setImageDrawable(value)
        }

    private fun initializeAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.WYBLabelEditText)
        typedArray.let {
            labelText = it.getString(R.styleable.WYBLabelEditText_labelText)
            showIcon = it.getBoolean(R.styleable.WYBLabelEditText_showIcon, false)
            iconType = when (it.getInt(R.styleable.WYBLabelEditText_iconType, TYPE_CHECK)) {
                TYPE_EDIT -> ResourcesCompat.getDrawable(resources, R.drawable.ic_edit, null)
                else -> ResourcesCompat.getDrawable(resources, R.drawable.ic_check_20, null)
            }
            backgroundStroke =
                when (it.getInt(R.styleable.WYBLabelEditText_backgroundStroke, COLOR_ORANGE)) {
                    COLOR_GRAY -> ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.shape_gray2_stroke,
                        null
                    )
                    else -> ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.shape_orange_stroke,
                        null
                    )
                }
            it.recycle()
        }
    }

    fun setTextFilter(maxLength: Int, hasPattern: Boolean) {
        val pattern = "^[_.a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]*$"
        val lengthFilter = InputFilter.LengthFilter(maxLength)
        val inputFilter = InputFilter { source, _, _, _, _, _ ->
            if (!source.matches(Regex(pattern))) return@InputFilter ""
            null
        }
        val filters = if (hasPattern) arrayOf(lengthFilter, inputFilter) else arrayOf(lengthFilter)
        binding.etInput.filters = filters
    }

    companion object {
        private const val COLOR_GRAY = 0
        private const val COLOR_ORANGE = 1
        private const val TYPE_EDIT = 0
        private const val TYPE_CHECK = 1
    }
}