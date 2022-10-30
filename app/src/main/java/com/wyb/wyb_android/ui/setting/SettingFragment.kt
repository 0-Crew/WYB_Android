package com.wyb.wyb_android.ui.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.BindingFragment
import com.wyb.wyb_android.databinding.FragmentSettingBinding

class SettingFragment : BindingFragment<FragmentSettingBinding>(
    R.layout.fragment_setting
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    private fun addListener() {
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.layoutProfile.setOnClickListener {
            findNavController().navigate(R.id.actionSettingToSettingProfile)
        }
        binding.layoutInquiry.layoutSettingMenuItem.setOnClickListener {
            val uri = getString(R.string.wyb_instagram)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }
    }
}