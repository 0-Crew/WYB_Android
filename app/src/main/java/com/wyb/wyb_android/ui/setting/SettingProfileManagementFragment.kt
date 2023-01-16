package com.wyb.wyb_android.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentSettingProfileManagementBinding
import com.wyb.wyb_android.ui.setting.SettingViewModel.Companion.MAX_NICKNAME_LENGTH


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
    }

    private fun initView() {
        with(binding.etNickname) {
            setTextMaxLength(MAX_NICKNAME_LENGTH)
        }
    }
}