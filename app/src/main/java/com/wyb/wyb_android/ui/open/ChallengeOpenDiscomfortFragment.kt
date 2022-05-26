package com.wyb.wyb_android.ui.open

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentChallengeOpenDiscomfortBinding
import com.wyb.wyb_android.ui.open.ChallengeOpenViewModel.Companion.MAX_INPUT_LENGTH
import com.wyb.wyb_android.widget.adapter.WYBPopupWindowItemAdapter

class ChallengeOpenDiscomfortFragment :
    ViewModelFragment<FragmentChallengeOpenDiscomfortBinding, ChallengeOpenViewModel>(R.layout.fragment_challenge_open_discomfort) {
    override val viewModel: ChallengeOpenViewModel by navGraphViewModels(R.id.challenge_open_nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initNavBar()
        initRecyclerView()
        addListeners()
        addObserver()

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

    private fun initRecyclerView() {
        val discomfortItemAdapter = WYBPopupWindowItemAdapter(
            requireContext(),
            WYBPopupWindowItemAdapter.TYPE_POPUP_DEFAULT
        )
        binding.rvDiscomfort.adapter = discomfortItemAdapter
        binding.rvDiscomfort.layoutManager = LinearLayoutManager(context)
    }

    private fun addListeners() {
        binding.etDiscomfort.addOnGlobalLayoutListener(binding.scrollView)
        binding.includeNavBar.btnNav.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.actionChallengeOpenDiscomfortToChallengeOpenStartDate)
        }
    }

    private fun addObserver() {
        viewModel.discomfortPos.observe(viewLifecycleOwner) { position ->
            if (position == 11) {
                binding.etDiscomfort.setTextMaxLength(MAX_INPUT_LENGTH)
                binding.etDiscomfort.setEditTextFocusable()
            } else {
                binding.etDiscomfort.clearTextMaxLength()
                binding.etDiscomfort.setEditTextNotFocusable(requireActivity())
            }
        }
    }
}