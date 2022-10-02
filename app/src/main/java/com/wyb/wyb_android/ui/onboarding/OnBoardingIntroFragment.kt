package com.wyb.wyb_android.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.BindingFragment
import com.wyb.wyb_android.databinding.FragmentOnboardingIntroBinding

class OnBoardingIntroFragment : BindingFragment<FragmentOnboardingIntroBinding>(
    R.layout.fragment_onboarding_intro
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.actionOnBoardingIntroFragmentToOnBoardingComfortFragment)
        }
    }
}