package com.wyb.wyb_android.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.BindingFragment
import com.wyb.wyb_android.databinding.FragmentOnboardingComfortBinding

class OnBoardingComfortFragment : BindingFragment<FragmentOnboardingComfortBinding>(
    R.layout.fragment_onboarding_comfort
) {
    private val animationDuration: Long = 700L
    private val animationDelayDuration: Long = 800L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
        initAlphaAnimation()
    }

    private fun initClickListener() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.actionOnBoardingComfortFragmentToOnBoardingChallengeFragment)
        }
    }

    private fun initAlphaAnimation() {
        binding.layoutComfort.animate()
            .alpha(1f)
            .setDuration(animationDuration)
            .setStartDelay(animationDelayDuration)
            .start()
    }
}