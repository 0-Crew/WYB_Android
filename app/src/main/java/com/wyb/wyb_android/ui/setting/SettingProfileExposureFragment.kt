package com.wyb.wyb_android.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentSettingProfileExposureBinding

class SettingProfileExposureFragment :
    ViewModelFragment<FragmentSettingProfileExposureBinding, SettingViewModel>(
        R.layout.fragment_setting_profile_exposure
    ) {
    override val viewModel: SettingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchUserExposure()
        addListener()
    }

    private fun addListener() {
        binding.layoutTitle.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnProfileExposure.setOnClickListener {
            viewModel.updateUserExposure()
        }
    }
}