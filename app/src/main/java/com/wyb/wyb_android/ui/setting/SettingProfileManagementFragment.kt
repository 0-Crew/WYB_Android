package com.wyb.wyb_android.ui.setting

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentSettingProfileManagementBinding
import com.wyb.wyb_android.ui.setting.SettingViewModel.Companion.MAX_NICKNAME_LENGTH
import kotlinx.android.synthetic.main.view_wyb_label_edit_text.view.*

class SettingProfileManagementFragment :
    ViewModelFragment<FragmentSettingProfileManagementBinding, SettingViewModel>(
        R.layout.fragment_setting_profile_management
    ) {
    override val viewModel: SettingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addListener()
    }

    private fun addListener() {
        binding.layoutTitle.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.etNickname.ivIcon.setOnClickListener {
            navigateToHomeFragment()
        }
        binding.etNickname.etInput.setOnEditorActionListener(
            OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    navigateToHomeFragment()
                    return@OnEditorActionListener true
                }
                false
            }
        )
    }

    private fun initView() {
        with(binding.etNickname) {
            setTextMaxLength(MAX_NICKNAME_LENGTH)
            etInput.setImeActionLabel(
                getString(R.string.complete),
                EditorInfo.IME_ACTION_DONE
            )
        }
    }

    private fun navigateToHomeFragment() {
        if(!binding.etNickname.ivIcon.isChecked) {
            findNavController().navigate(R.id.actionSettingProfileManagementToHome)
        }
    }
}