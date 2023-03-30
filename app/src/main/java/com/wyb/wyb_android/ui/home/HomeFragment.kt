package com.wyb.wyb_android.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentHomeBinding

class HomeFragment : ViewModelFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModels()

    private lateinit var challengeAdapter: HomeChallengeAdapter
    private lateinit var profileAdapter: HomeOtherProfileAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        challengeAdapter = HomeChallengeAdapter(viewModel, requireContext())
        profileAdapter = HomeOtherProfileAdapter()
        viewModel.fetchHomeDate()
        initHomeRVAdapter()
        setChallengeList()
        addListener()
    }

    private fun initHomeRVAdapter() {
        binding.rvOtherProfile.adapter = profileAdapter
        binding.rvDiscomfort.adapter = challengeAdapter
    }

    private fun setChallengeList() {
        viewModel.challengeList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(challengeAdapter) { submitList(list) }
            }
        }
    }

    private fun addListener() {
        binding.tvDate.setOnClickListener {
            findNavController().navigate(R.id.actionChallengeHomeToCalendar)
        }
        binding.btnMenu.setOnClickListener {
            findNavController().navigate(R.id.actionHomeToSetting)
        }
        binding.btnAlarm.setOnClickListener {
            findNavController().navigate(R.id.actionHomeToNotification)
        }
        binding.btnBottleWorld.setOnClickListener { navigateToBottleWorld() }
        binding.tvBottleWorld.setOnClickListener { navigateToBottleWorld() }
    }

    private fun navigateToBottleWorld() {
        findNavController().navigate(R.id.actionHomeToBottleWorld)
    }
}