package com.wyb.wyb_android.ui.bottleworld

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BottleWorldPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        PAGE_BOTTLE_WORLD_ALL to { BottleWorldContainerFragment() },
        PAGE_BOTTLE_WORLD_FOLLOWER to { BottleWorldContainerFragment() },
        PAGE_BOTTLE_WORLD_FOLLOWING to { BottleWorldContainerFragment() }
    )

    override fun getItemCount(): Int = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

    companion object {
        private const val PAGE_BOTTLE_WORLD_ALL = 0
        private const val PAGE_BOTTLE_WORLD_FOLLOWER = 1
        private const val PAGE_BOTTLE_WORLD_FOLLOWING = 2
    }
}