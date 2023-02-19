package com.wyb.wyb_android.ui.bottleworld

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.BindingFragment
import com.wyb.wyb_android.databinding.FragmentBottleWorldBinding

class BottleWorldFragment : BindingFragment<FragmentBottleWorldBinding>(
    R.layout.fragment_bottle_world
) {

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

    private fun setTabLayoutText(position: Int) : String {
        return when(position) {
            1 -> "0 " + getString(R.string.bottle_world_follower)
            2 -> "0 " + getString(R.string.bottle_world_following)
            else -> getString(R.string.bottle_world_all)
        }
    }
}