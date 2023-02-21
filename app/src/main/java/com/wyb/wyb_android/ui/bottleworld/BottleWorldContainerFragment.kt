package com.wyb.wyb_android.ui.bottleworld

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentBottleWorldContainerBinding
import com.wyb.wyb_android.ui.bottleworld.BottleWorldPagerAdapter.Companion.TAB_BOTTLE_WORLD_BROWSE
import com.wyb.wyb_android.ui.bottleworld.BottleWorldPagerAdapter.Companion.TAB_BOTTLE_WORLD_FOLLOWER
import com.wyb.wyb_android.ui.bottleworld.BottleWorldPagerAdapter.Companion.TAB_BOTTLE_WORLD_FOLLOWING

class BottleWorldContainerFragment(private val tabMode: Int) :
    ViewModelFragment<FragmentBottleWorldContainerBinding, BottleWorldViewModel>(
        R.layout.fragment_bottle_world_container
    ) {

    override val viewModel: BottleWorldViewModel by viewModels()
    private lateinit var bottleWorldListAdapter: BottleWorldListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottleWorldListAdapter = BottleWorldListAdapter()
        viewModel.fetchChallengeList()
        initRVAdapter()
        setListToAdapter()
    }

    private fun initRVAdapter() {
        binding.rvBottleWorld.adapter = bottleWorldListAdapter
    }

    private fun setListToAdapter() {
        when (tabMode) {
            TAB_BOTTLE_WORLD_BROWSE -> {
                viewModel.browseList.observe(viewLifecycleOwner) { list ->
                    list?.let {
                        with(bottleWorldListAdapter) { submitList(list) }
                    }
                }
            }
            TAB_BOTTLE_WORLD_FOLLOWER -> {
                viewModel.followerList.observe(viewLifecycleOwner) { list ->
                    list?.let {
                        with(bottleWorldListAdapter) { submitList(list) }
                    }
                }
            }
            TAB_BOTTLE_WORLD_FOLLOWING -> {
                viewModel.followingList.observe(viewLifecycleOwner) { list ->
                    list?.let {
                        with(bottleWorldListAdapter) { submitList(list) }
                    }
                }
            }
        }
    }
}