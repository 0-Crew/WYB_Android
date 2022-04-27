package com.wyb.wyb_android.ui.open

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentChallengeOpenDiscomfortBinding
import com.wyb.wyb_android.util.showPopupWindow

class ChallengeOpenDiscomfortFragment :
    ViewModelFragment<FragmentChallengeOpenDiscomfortBinding, ChallengeOpenViewModel>(R.layout.fragment_challenge_open_discomfort) {
    override val viewModel: ChallengeOpenViewModel by navGraphViewModels(R.id.challenge_open_nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initNavBar()
        binding.etDiscomfort.post { binding.etDiscomfort.showPopupWindow(requireContext()) }

        return binding.root
    }

    private fun initNavBar() {
        binding.includeNavBar.apply {
            btnNav.setImageResource(R.drawable.ic_arrow_left)
            tvNavTitle.text = getString(R.string.challenge_open_discomfort_nav_title)
            ivBottle.setImageResource(R.drawable.ic_nav_bottle_washing)
            progressBar.progress = 2
        }
    }
}