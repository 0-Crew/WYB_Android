package com.wyb.wyb_android.ui.setting

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.BindingFragment
import com.wyb.wyb_android.databinding.FragmentSettingTermsBinding

class SettingTermsFragment : BindingFragment<FragmentSettingTermsBinding>(
    R.layout.fragment_setting_terms
) {
    private val args: SettingTermsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
        addListener()
    }

    private fun initWebView() {
        binding.layoutTitle.titleString = getString(args.termsType.title)
        binding.layoutWebView.loadUrl(getString(args.termsType.url))
    }

    private fun addListener() {
        binding.layoutTitle.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}