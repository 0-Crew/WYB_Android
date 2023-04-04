package com.wyb.wyb_android.ui.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.data.type.TermsType
import com.wyb.wyb_android.databinding.FragmentSettingBinding

class SettingFragment : ViewModelFragment<FragmentSettingBinding, SettingViewModel>(
    R.layout.fragment_setting
) {
    override val viewModel: SettingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    private fun addListener() {
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.layoutProfile.setOnClickListener {
            findNavController().navigate(
                SettingFragmentDirections.actionSettingToSettingProfile(
                    viewModel.userNickname.value.orEmpty()
                )
            )
        }
        binding.layoutInquiry.layoutSettingMenuItem.setOnClickListener {
            val uri = getString(R.string.wyb_instagram)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }
        binding.layoutPrivacy.layoutSettingMenuItem.setOnClickListener {
            setNavigateSettingTermsFragment(TermsType.PRIVACY_POLICY)
        }
        binding.layoutServiceTerm.layoutSettingMenuItem.setOnClickListener {
            setNavigateSettingTermsFragment(TermsType.SERVICE_TERMS)
        }
    }

    private fun setNavigateSettingTermsFragment(type: TermsType) {
        val directions = SettingFragmentDirections.actionSettingToSettingTerms(type)
        findNavController().navigate(directions)
    }
}