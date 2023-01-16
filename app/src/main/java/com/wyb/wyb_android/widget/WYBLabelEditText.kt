package com.wyb.wyb_android.widget

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.wyb.wyb_android.R
import com.wyb.wyb_android.databinding.ViewWybLabelEditTextBinding
import com.wyb.wyb_android.util.Utils
import com.wyb.wyb_android.util.hideKeyboard

class WYBLabelEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding: ViewWybLabelEditTextBinding =
        ViewWybLabelEditTextBinding.inflate(LayoutInflater.from(context), this, false)

    private var maxLength = 0
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s == null) return
            if (s.length > maxLength) {
                binding.etInput.setText(s.subSequence(0, maxLength))
                binding.etInput.setSelection(maxLength)
            }
        }
    }

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

    var hint: String?
        get() = binding.etInput.hint.toString()
        set(value) {
            binding.etInput.hint = value
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
            hint = it.getString(R.styleable.WYBLabelEditText_hint)
            showIcon = it.getBoolean(R.styleable.WYBLabelEditText_showIcon, false)
            iconType = when (it.getInt(R.styleable.WYBLabelEditText_iconType, TYPE_CHECK)) {
                TYPE_EDIT -> ResourcesCompat.getDrawable(resources, R.drawable.ic_edit, null)
                else -> ResourcesCompat.getDrawable(resources, R.drawable.ic_check_20, null)
            }
            setBackgroundStroke(it.getInt(R.styleable.WYBLabelEditText_backgroundStroke, COLOR_ORANGE))
            it.recycle()
        }
    }

    private fun isKeyboardShown(): Boolean {
        val rootView = binding.etInput.rootView
        val softKeyboardHeight = 100
        val r = Rect()
        rootView.getWindowVisibleDisplayFrame(r)
        val dm = rootView.resources.displayMetrics
        val heightDiff = rootView.bottom - r.bottom
        return heightDiff > softKeyboardHeight * dm.density
    }

    fun setTextInputFilter() {
        val pattern = "^[_.a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\u318D\\u119E\\u11A2\\u2022\\u2025\\u00B7\\uFE55\\u3161\\u3163]*$"
        val inputFilter = InputFilter { source, _, _, _, _, _ ->
            if (!source.matches(Regex(pattern))) return@InputFilter ""
            null
        }
        val filters = arrayOf(inputFilter)
        binding.etInput.filters = filters
    }

    fun setTextMaxLength(maxLength: Int) {
        this.maxLength = maxLength
        binding.etInput.addTextChangedListener(textWatcher)
    }

    fun clearTextMaxLength() {
        binding.etInput.removeTextChangedListener(textWatcher)
    }

    fun setBackgroundStroke(color: Int) {
        backgroundStroke = when (color) {
            COLOR_GRAY -> ResourcesCompat.getDrawable(resources, R.drawable.shape_gray2_stroke, null)
            else -> ResourcesCompat.getDrawable(resources, R.drawable.shape_orange_stroke, null)
        }
    }

    fun setOnFocusChangeListener(activity: Activity?) {
        binding.layout.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Utils.requestFocus(binding.etInput)
            }
        }
        binding.etInput.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(activity, v)
            }
        }
    }

    fun addOnGlobalLayoutListener(scrollView: ScrollView) {
        binding.etInput.viewTreeObserver.addOnGlobalLayoutListener {
            if (isKeyboardShown()) {
                scrollToBottom(scrollView)
            }
        }
    }

    private fun scrollToBottom(scrollView: ScrollView) {
        scrollView.apply {
            val lastChild = getChildAt(0)
            val bottom = lastChild.bottom + paddingBottom
            val delta = bottom - (scrollY + height)
            smoothScrollTo(0, delta)
        }
    }

    fun setEditTextFocusable() {
        binding.etInput.isFocusable = true
        binding.etInput.isFocusableInTouchMode = true
        Utils.requestFocus(binding.etInput)
    }

    fun setEditTextNotFocusable(activity: Activity?) {
        binding.etInput.isFocusable = false
        binding.etInput.isFocusableInTouchMode = false
        hideKeyboard(activity, binding.etInput)
    }

    companion object {
        private const val COLOR_GRAY = 0
        private const val COLOR_ORANGE = 1
        private const val TYPE_EDIT = 0
        private const val TYPE_CHECK = 1
    }
}