package com.wyb.wyb_android.ui.open

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentChallengeOpenStartDateBinding

class ChallengeOpenStartDateFragment :
    ViewModelFragment<FragmentChallengeOpenStartDateBinding, ChallengeOpenViewModel>(R.layout.fragment_challenge_open_start_date) {
    override val viewModel: ChallengeOpenViewModel by navGraphViewModels(R.id.challenge_open_nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initNavBar()
        addListeners()

        return binding.root
    }

    private fun initNavBar() {
        binding.includeNavBar.apply {
            btnNav.setImageResource(R.drawable.ic_arrow_left)
            tvNavTitle.text = getString(R.string.challenge_open_start_date_nav_title)
            ivBottle.setImageResource(R.drawable.ic_nav_bottle)
            progressBar.progress = 3
        }
    }

    private fun addListeners() {
        binding.includeNavBar.btnNav.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnStart.setOnClickListener {
            viewModel.postChallengeOpen()
            viewModel.validServer.observe(viewLifecycleOwner) { isSuccess ->
                if (isSuccess) {
                    findNavController().navigate(R.id.actionChallengeOpenStartDateToChallengeHome)
                }
            }
        }
    }
}