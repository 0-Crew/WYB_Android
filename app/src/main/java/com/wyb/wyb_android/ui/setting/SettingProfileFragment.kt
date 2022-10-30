package com.wyb.wyb_android.ui.setting

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.BindingFragment
import com.wyb.wyb_android.databinding.FragmentSettingProfileBinding

class SettingProfileFragment : BindingFragment<FragmentSettingProfileBinding>(
    R.layout.fragment_setting_profile
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    private fun addListener() {
        binding.layoutTitle.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.layoutProfileExposure.layoutSettingMenuItem.setOnClickListener {
            findNavController().navigate(R.id.actionSettingProfileToSettingProfileExposure)
        }
        binding.layoutProfileManagement.layoutSettingMenuItem.setOnClickListener {
            findNavController().navigate(R.id.actionSettingProfileToSettingProfileManagement)
        }
    }
}