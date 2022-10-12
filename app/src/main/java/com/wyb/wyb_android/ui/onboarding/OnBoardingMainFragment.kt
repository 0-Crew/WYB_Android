package com.wyb.wyb_android.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.BindingFragment
import com.wyb.wyb_android.databinding.FragmentOnboardingMainBinding

class OnBoardingMainFragment : BindingFragment<FragmentOnboardingMainBinding>(
    R.layout.fragment_onboarding_main
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor = requireContext().getColor(R.color.orange)
        initClickListener()
    }

    private fun initClickListener() {
        binding.layoutOnBoarding.setOnClickListener {
            findNavController().navigate(R.id.actionOnBoardingMainToOnBoardingIntro)
        }
    }
}