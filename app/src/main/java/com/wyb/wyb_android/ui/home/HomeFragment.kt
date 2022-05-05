package com.wyb.wyb_android.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.data.model.Challenge
import com.wyb.wyb_android.databinding.FragmentHomeBinding

class HomeFragment : ViewModelFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        setOtherProfileRVAdapter()
        setChallengeRVAdapter()
        return binding.root
    }

    private fun setOtherProfileRVAdapter() {
        binding.rvOtherProfile.adapter = HomeOtherProfileAdapter()
    }

    private fun setChallengeRVAdapter() {
        val challengeAdapter = HomeChallengeAdapter(viewModel)
        binding.rvDiscomfort.adapter = challengeAdapter
        val itemList = mutableListOf(
            Challenge(1, "불편함1", false, isToday = false, isFuture = false, 1),
            Challenge(2, "불편함1", false, isToday = false, isFuture = false, 2),
            Challenge(3, "불편함1", true, isToday = false, isFuture = false, 3),
            Challenge(4, "불편함1", false, isToday = false, isFuture = false, 4),
            Challenge(5, "불편함1", false, isToday = true, isFuture = false, 5),
            Challenge(6, "불편함1", false, isToday = false, isFuture = true, 6),
            Challenge(7, "불편함1", false, isToday = false, isFuture = true, 7)
        )
        challengeAdapter.submitList(itemList)
    }
}