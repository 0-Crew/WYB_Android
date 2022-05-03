package com.wyb.wyb_android.ui.open

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentChallengeOpenDiscomfortBinding
import com.wyb.wyb_android.extension.showPopupWindow

class ChallengeOpenDiscomfortFragment :
    ViewModelFragment<FragmentChallengeOpenDiscomfortBinding, ChallengeOpenViewModel>(R.layout.fragment_challenge_open_discomfort) {
    override val viewModel: ChallengeOpenViewModel by navGraphViewModels(R.id.challenge_open_nav_graph)
    private lateinit var popupWindow: PopupWindow

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initNavBar()
        initPopupWindow()
        addListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        popupWindow.dismiss()
    }

    private fun initNavBar() {
        binding.includeNavBar.apply {
            btnNav.setImageResource(R.drawable.ic_arrow_left)
            tvNavTitle.text = getString(R.string.challenge_open_discomfort_nav_title)
            ivBottle.setImageResource(R.drawable.ic_nav_bottle_washing)
            progressBar.progress = 2
        }
    }

    private fun initPopupWindow() {
        binding.etDiscomfort.post {
            popupWindow = binding.etDiscomfort.showPopupWindow(requireContext())
        }
    }

    private fun addListeners() {
        binding.includeNavBar.btnNav.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.actionChallengeOpenDiscomfortToChallengeOpenStartDate)
        }
    }
}