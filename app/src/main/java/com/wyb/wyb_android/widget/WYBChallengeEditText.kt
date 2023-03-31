package com.wyb.wyb_android.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.wyb.wyb_android.R
import com.wyb.wyb_android.databinding.ViewWybChallengeEditTextBinding
import com.wyb.wyb_android.util.Utils

class WYBChallengeEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val binding: ViewWybChallengeEditTextBinding =
        ViewWybChallengeEditTextBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
        initializeAttrs(context, attrs)
        checkWritingState()
    }

    var isToday: Drawable?
        get() = binding.layout.background
        set(value) {
            binding.layout.background = value
        }

    var setEtStyle: Int
        get() = binding.etDiscomfort.typeface.style
        set(value) {
            binding.etDiscomfort.setTextAppearance(value)
        }

    private fun initializeAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.WYBChallengeEditText)
        typedArray.let {
            setChallengeToday(
                it.getInt(
                    R.styleable.WYBChallengeEditText_setBackgroundRes,
                    STROKE_ORANGE
                )
            )

            setEtStyle =
                when (it.getInt(R.styleable.WYBChallengeEditText_setEtStyle, CHALLENGE_NOW)) {
                    CHALLENGE_END -> {
                        setIconSize(R.drawable.ic_check_16, R.drawable.ic_x_16)
                        R.style.TextAppearance_WYBComponents_Bold_12
                    }
                    else -> {
                        setIconSize(R.drawable.ic_check_20, R.drawable.ic_x_20)
                        R.style.TextAppearance_WYBComponents_Bold_14
                    }
                }
            it.recycle()
        }
    }

    private fun setDrawableRes(drawableRes: Int): Drawable? {
        return ResourcesCompat.getDrawable(
            resources,
            drawableRes,
            null
        )
    }

    private fun setIconSize(checkDrawable: Int, closeDrawable: Int) {
        with(binding) {
            btnCheck.setImageResource(checkDrawable)
            btnClose.setImageResource(closeDrawable)
        }
    }

    private fun setViewColor(colorRes: Int) {
        with(binding) {
            etDiscomfort.setTextColor(resources.getColor(colorRes, null))
            btnCheck.setColorFilter(
                ContextCompat.getColor(
                    context,
                    colorRes
                )
            )
        }
    }

    private fun checkWritingState() {
        with(binding) {
            etDiscomfort.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    when (count) {
                        0 -> {
                            btnCheck.visibility = View.GONE
                            btnClose.visibility = View.VISIBLE
                        }

                        else -> {
                            btnClose.visibility = View.GONE
                            btnCheck.visibility = View.VISIBLE
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    fun setChallengeToday(color: Int) {
        isToday = when (color) {
            STROKE_GRAY -> {
                setViewColor(R.color.gray_4)
                setDrawableRes(R.drawable.shape_gray4_stroke)
            }
            else -> {
                setViewColor(R.color.orange)
                setDrawableRes(R.drawable.shape_orange_stroke)
            }
        }
    }

    fun requestDiscomfortFocus() {
        binding.etDiscomfort.requestFocus()
        binding.layout.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Utils.requestFocus(binding.etDiscomfort)
            }
        }
        binding.etDiscomfort.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                Utils.clearFocus(view)
            }
        }
    }

    companion object {
        private const val STROKE_ORANGE = 0
        private const val STROKE_GRAY = 1
        private const val CHALLENGE_NOW = 0
        private const val CHALLENGE_END = 1
    }
}