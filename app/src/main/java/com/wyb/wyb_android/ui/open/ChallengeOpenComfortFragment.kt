package com.wyb.wyb_android.ui.open

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentChallengeOpenComfortBinding
import com.wyb.wyb_android.ui.open.ChallengeOpenViewModel.Companion.MAX_INPUT_LENGTH

class ChallengeOpenComfortFragment :
    ViewModelFragment<FragmentChallengeOpenComfortBinding, ChallengeOpenViewModel>(R.layout.fragment_challenge_open_comfort) {
    override val viewModel: ChallengeOpenViewModel by navGraphViewModels(R.id.challenge_open_nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initNavBar()
        initEditText()
        addListeners()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRandomHintString()
    }

    private fun initNavBar() {
        binding.includeNavBar.apply {
            btnNav.setImageResource(R.drawable.ic_x_24)
            tvNavTitle.text = getString(R.string.challenge_open_comfort_nav_title)
            ivBottle.setImageResource(R.drawable.ic_nav_bottle_washing)
            progressBar.progress = 1
        }
    }

    private fun initEditText() {
        binding.etComfort.setTextMaxLength(MAX_INPUT_LENGTH)
    }

    private fun addListeners() {
        binding.etComfort.setOnFocusChangeListener(requireActivity())
        binding.includeNavBar.btnNav.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.actionChallengeOpenComfortToChallengeOpenDiscomfort)
        }
    }
}