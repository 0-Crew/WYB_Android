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
        challengeAdapter = HomeChallengeAdapter(requireContext())
        profileAdapter = HomeOtherProfileAdapter()
        viewModel.fetchHomeDate()
        initHomeRVAdapter()
        setChallengeList()
        addListener()
        setProfileItemClickListener()
        setChallengeAdapterClickListener()
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
        viewModel.followingList.observe(viewLifecycleOwner) { list ->
            list.let { profileAdapter.submitList(list) }
        }
    }

    private fun addListener() {
        binding.tvDate.setOnClickListener { navigateToCalender() }
        binding.btnCalender.setOnClickListener { navigateToCalender() }
        binding.btnMenu.setOnClickListener {
            findNavController().navigate(R.id.actionHomeToSetting)
        }
        binding.btnAlarm.setOnClickListener {
            findNavController().navigate(R.id.actionHomeToNotification)
        }
        binding.btnBottleWorld.setOnClickListener { navigateToBottleWorld() }
        binding.tvBottleWorld.setOnClickListener { navigateToBottleWorld() }
        binding.btnNewChallenge.setOnClickListener {
            findNavController().navigate(R.id.actionHomeToChallengeOpen)
        }
    }

    private fun setProfileItemClickListener() {
        profileAdapter.setItemClickListener(object :
            HomeOtherProfileAdapter.OnItemClickListener {
            override fun onItemClick(userId: Int) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeToUserHome(userId)
                )
            }
        })
    }

    private fun setChallengeAdapterClickListener() {
        challengeAdapter.setItemClickListener(object :
            HomeChallengeAdapter.OnItemClickListener {
            override fun onWaterDropClick(discomfortId: Int) {
                viewModel.postChallengeFinished(discomfortId)
                viewModel.setIsSuccess(discomfortId)
            }

            override fun onEditIconClick(discomfortId: Int, discomfortTitle: String) {
                viewModel.updateChallengeTitle(discomfortId, discomfortTitle)
            }
        })
    }

    private fun navigateToBottleWorld() {
        findNavController().navigate(R.id.actionHomeToBottleWorld)
    }

    private fun navigateToCalender() {
        findNavController().navigate(R.id.actionChallengeHomeToCalendar)
    }
}