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
        setChallengeRVAdapter()
        return binding.root
    }

    private fun setChallengeRVAdapter() {
        val challengeAdapter = HomeChallengeAdapter(homeViewModel)
        val itemList = mutableListOf(
            Challenge("불편함1", "06", isToday = false, isFuture = false),
            Challenge("불편함1", "07", isToday = false, isFuture = false),
            Challenge("불편함1", "08", isToday = false, isFuture = false),
            Challenge("불편함1", "09", isToday = false, isFuture = false),
            Challenge("불편함1", "10", isToday = true, isFuture = false),
            Challenge("불편함1", "11", isToday = false, isFuture = true),
            Challenge("불편함1", "12", isToday = false, isFuture = true)
        )

        challengeAdapter.submitList(itemList)
    }
}