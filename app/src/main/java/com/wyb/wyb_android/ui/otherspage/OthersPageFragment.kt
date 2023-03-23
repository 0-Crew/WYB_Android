package com.wyb.wyb_android.ui.otherspage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentOthersPageBinding
import com.wyb.wyb_android.widget.WYBToast

class OthersPageFragment :
    ViewModelFragment<FragmentOthersPageBinding, OthersPageViewModel>(R.layout.fragment_others_page) {
    override val viewModel: OthersPageViewModel by viewModels()

    private val args: OthersPageFragmentArgs by navArgs()

    private lateinit var challengeAdapter: OthersPageChallengeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchUserHome(args.userId)
        initAdapter()
        setListener()
        initLayout()
    }

    private fun initAdapter() {
        challengeAdapter = OthersPageChallengeAdapter()
        binding.rvChallenge.adapter = challengeAdapter
        viewModel.discomfortList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(challengeAdapter) { submitList(list) }
            }
        }
    }

    private fun setListener() {
        binding.layoutTitle.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.tvTerm.setOnClickListener {
            navigateToCalendar()
        }
        binding.layoutCalender.setOnClickListener {
            navigateToCalendar()
        }
        binding.layoutCheer.setOnClickListener {
            viewModel.postCheer(args.userId)
            WYBToast.createToast(
                requireContext(),
                getString(R.string.notification_toast_cheer, viewModel.nickname.value)
            ).show()
        }
        binding.cbFollow.setOnClickListener {
            viewModel.postFollow(args.userId)
        }
    }

    private fun initLayout() {
        viewModel.challengeComfort.observe(viewLifecycleOwner) { comfort ->
            binding.layoutChallengeComfort.setComfortTitleText(comfort)
        }
    }

    private fun navigateToCalendar() {
        findNavController().navigate(
            OthersPageFragmentDirections.actionOthersPageToCalendar(
                userId = args.userId
            )
        )
    }
}