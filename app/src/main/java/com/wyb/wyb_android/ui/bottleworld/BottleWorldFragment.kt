package com.wyb.wyb_android.ui.bottleworld

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentBottleWorldBinding
import com.wyb.wyb_android.ui.bottleworld.BottleWorldPagerAdapter.Companion.TAB_BOTTLE_WORLD_FOLLOWER
import com.wyb.wyb_android.ui.bottleworld.BottleWorldPagerAdapter.Companion.TAB_BOTTLE_WORLD_FOLLOWING

class BottleWorldFragment : ViewModelFragment<FragmentBottleWorldBinding, BottleWorldViewModel>(
    R.layout.fragment_bottle_world
) {
    override val viewModel: BottleWorldViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
        initViewPagerAdapter()
    }

    private fun addListener() {
        binding.layoutTitle.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViewPagerAdapter() {
        val viewPager = binding.viewPagerBottleWorld
        val tabLayout = binding.tabLayoutBottleWorld

        viewPager.adapter = BottleWorldPagerAdapter(this)
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = setTabLayoutText(position)
        }.attach()
    }

    private fun setTabLayoutText(tabMode: Int): String {
        return when (tabMode) {
            TAB_BOTTLE_WORLD_FOLLOWER -> viewModel.countFollower.value.toString() + " " + getString(R.string.bottle_world_follower)
            TAB_BOTTLE_WORLD_FOLLOWING -> viewModel.countFollowing.value.toString() + " " + getString(R.string.bottle_world_following)
            else -> getString(R.string.bottle_world_all)
        }
    }
}