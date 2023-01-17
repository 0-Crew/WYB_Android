package com.wyb.wyb_android.ui.setting.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.wyb.wyb_android.R
import com.wyb.wyb_android.databinding.DialogTwoButtonBinding

class TwoButtonDialog(
    private val dialogMode: Int,
) : DialogFragment() {
    private lateinit var binding: DialogTwoButtonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogTwoButtonBinding.inflate(layoutInflater, container, false)
        initView()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setLayout()
    }

    private fun setLayout() {
        val dialogWidth = (resources.displayMetrics.widthPixels * 0.8).toInt()
        requireNotNull(dialog).apply {
            requireNotNull(window).apply {
                setLayout(
                    dialogWidth,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }
    }

    private fun initView() {
        when (dialogMode) {
            WITHDRAWAL -> setText(
                title = getString(R.string.withdraw_title),
                titleSub = getString(R.string.withdraw_title_sub),
            )
        }
    }

    private fun setText(title: String, titleSub: String) {
        binding.tvTitle.text = title
        binding.tvTitleSub.text = titleSub
    }

    companion object {
        const val WITHDRAWAL = 0
    }
}