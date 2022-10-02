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
    private val animationDurationFirst: Long = 800L
    private val animationDurationSecond: Long = 900L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
        initAlphaAnimation()
    }

    private fun initClickListener() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.actionOnBoardingIntroFragmentToOnBoardingComfortFragment)
        }
    }

    private fun initAlphaAnimation() {
        binding.tvSub1.animate()
            .alpha(1f)
            .setDuration(animationDurationFirst)
            .start()

        binding.tvSub2.animate()
            .alpha(1f)
            .setDuration(animationDurationSecond)
            .setStartDelay(animationDurationFirst)
            .start()
    }
}