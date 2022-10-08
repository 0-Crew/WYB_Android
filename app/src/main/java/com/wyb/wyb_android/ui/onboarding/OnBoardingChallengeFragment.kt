package com.wyb.wyb_android.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentOnboardingChallengeBinding
import com.wyb.wyb_android.util.Utils.setDateText

class OnBoardingChallengeFragment :
    ViewModelFragment<FragmentOnboardingChallengeBinding, OnBoardingViewModel>(R.layout.fragment_onboarding_challenge) {

    override val viewModel: OnBoardingViewModel by navGraphViewModels(R.id.onboarding_nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDateView()
        initClickListener()
        setCoachMarkVisibility()
    }

    private fun initDateView() {
        val sixBeforeDate = viewModel.challengeDates.first()
        val todayDate = viewModel.challengeDates.last()
        binding.tvTerm.text = setDateText(sixBeforeDate, todayDate)
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