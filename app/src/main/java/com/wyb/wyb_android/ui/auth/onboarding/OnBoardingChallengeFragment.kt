package com.wyb.wyb_android.ui.auth.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentOnboardingChallengeBinding
import com.wyb.wyb_android.ui.auth.AuthViewModel
import com.wyb.wyb_android.util.Utils.setDateText

class OnBoardingChallengeFragment :
    ViewModelFragment<FragmentOnboardingChallengeBinding, AuthViewModel>(R.layout.fragment_onboarding_challenge) {

    override val viewModel: AuthViewModel by navGraphViewModels(R.id.auth_nav_graph)

    private lateinit var waterDropViews: List<CheckBox>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        initWaterCheckBoxArray()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDateView()
        initClickListener()
        setCoachMarkVisibility()
    }

    private fun initWaterCheckBoxArray() {
        waterDropViews = listOf(
            binding.cbWater1,
            binding.cbWater2,
            binding.cbWater3,
            binding.cbWater4,
            binding.cbWater5,
            binding.cbWater6,
            binding.cbWater7
        )
    }

    private fun initDateView() {
        val challengeDates = viewModel.challengeDates
        val sixBeforeDate = challengeDates.first()
        val todayDate = challengeDates.last()
        binding.tvTerm.text = setDateText(sixBeforeDate, todayDate)

        for ((index, view) in waterDropViews.withIndex()) {
            view.text = challengeDates[index].dayOfMonth.toString()
        }
    }

    private fun initClickListener() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.actionOnBoardingChallengeToOnBoardingWash)
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