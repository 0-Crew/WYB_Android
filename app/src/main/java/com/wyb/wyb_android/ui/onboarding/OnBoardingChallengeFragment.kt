package com.wyb.wyb_android.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentOnboardingChallengeBinding

class OnBoardingChallengeFragment :
    ViewModelFragment<FragmentOnboardingChallengeBinding, OnBoardingViewModel>(R.layout.fragment_onboarding_challenge) {

    override val viewModel: OnBoardingViewModel by navGraphViewModels(R.id.onboarding_nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
        setCoachMarkVisibility()
    }

    private fun initClickListener() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.actionOnBoardingChallengeFragmentToOnBoardingWashFragment)
        }
    }

    private fun setCoachMarkVisibility() {
        viewModel.levelOfJuice.observe(viewLifecycleOwner) { level ->
            if (level > 1 && viewModel.coachMark.value == true) {
                viewModel.removeCoachMark()
            }
        }
    }
}