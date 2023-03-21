package com.wyb.wyb_android.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.wyb.wyb_android.R
import com.wyb.wyb_android.databinding.ViewWybCompactTextButtonBinding

class WYBCompactTextButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding: ViewWybCompactTextButtonBinding =
        ViewWybCompactTextButtonBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
        initializeAttrs(context, attrs)
    }

    private var setBackgroundColor: Drawable?
        get() = binding.layout.background
        set(value) {
            binding.layout.background = value
        }

    var showIcon: Boolean
        get() = binding.ivIcon.isVisible
        set(value) {
            binding.ivIcon.isVisible = value
        }

    private var setIcon: Drawable?
        get() = binding.ivIcon.drawable
        set(value) {
            binding.ivIcon.setImageDrawable(value)
        }

    private var titleText: String?
        get() = binding.tvTitle.text.toString()
        set(value) {
            binding.tvTitle.text = value
        }

    private var showComfortTv: Boolean
        get() = binding.layoutComfort.isVisible
        set(value) {
            binding.layoutComfort.isVisible = value
        }

    private var comfortText: String?
        get() = binding.tvComfort.text.toString()
        set(value) {
            binding.tvComfort.text = value
        }

    private var textStyle: Int
        get() = binding.tvTitle.typeface.style
        set(value) {
            binding.tvTitle.setTextAppearance(value)
        }

    private fun initializeAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.WYBCompactTextButton)
        typedArray.let {
            titleText = it.getString(R.styleable.WYBCompactTextButton_titleText)
            comfortText = it.getString(R.styleable.WYBCompactTextButton_comfortText)
            showComfortTv = it.getBoolean(R.styleable.WYBCompactTextButton_showComfortTv, false)
            showIcon = it.getBoolean(R.styleable.WYBCompactTextButton_showStartIcon, false)
            setIcon =
                when (it.getInt(R.styleable.WYBCompactTextButton_setIcon, ICON_STAR_WHITE)) {
                    ICON_CALENDER -> setDrawableRes(R.drawable.ic_calendar)
                    else -> setDrawableRes(R.drawable.ic_star)
                }

            setBackgroundColor =
                when (it.getInt(R.styleable.WYBCompactTextButton_backgroundRes, COLOR_ORANGE)) {
                    COLOR_WHITE -> {
                        binding.ivIcon.setColorFilter(
                            ContextCompat.getColor(
                                context,
                                R.color.dark_gray_2
                            )
                        )
                        setColorTvTitle(R.color.dark_gray_2)
                        setDrawableRes(R.color.white)
                    }
                    COLOR_ORANGE_STROKE -> {
                        setColorTvTitle(R.color.orange)
                        setDrawableRes(R.drawable.shape_orange_stroke)
                    }
                    COLOR_GRAY -> {
                        binding.ivIcon.setColorFilter(
                            ContextCompat.getColor(
                                context,
                                R.color.white
                            )
                        )
                        setColorTvTitle(R.color.white)
                        setDrawableRes(R.color.gray_1)
                    }
                    else -> {
                        binding.ivIcon.setColorFilter(
                            ContextCompat.getColor(
                                context,
                                R.color.white
                            )
                        )
                        setColorTvTitle(R.color.white)
                        setDrawableRes(R.color.orange)
                    }
                }

            textStyle =
                when (it.getInt(R.styleable.WYBCompactTextButton_setTitleStyle, FONT_BOLD_16)) {
                    FONT_MID_14 -> R.style.TextAppearance_WYBComponents_Medium_14
                    FONT_BOLD_12 -> R.style.TextAppearance_WYBComponents_Bold_12
                    FONT_MID_12 -> R.style.TextAppearance_WYBComponents_Medium_12
                    else -> R.style.TextAppearance_WYBComponents_Bold_16
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

    private fun setColorTvTitle(colorRes: Int) {
        binding.tvTitle.setTextColor(resources.getColor(colorRes, null))
    }

    fun setTvTitle(text: String) {
        binding.tvTitle.text = text
    }

    companion object {
        private const val COLOR_ORANGE = 0
        private const val COLOR_WHITE = 1
        private const val COLOR_ORANGE_STROKE = 2
        private const val COLOR_GRAY = 3
        private const val ICON_STAR_WHITE = 0
        private const val ICON_CALENDER = 1
        private const val FONT_BOLD_16 = 0
        private const val FONT_MID_14 = 1
        private const val FONT_BOLD_12 = 2
        private const val FONT_MID_12 = 3
    }
}