package com.wyb.wyb_android.ui.bottleworld

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BottleWorldPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        TAB_BOTTLE_WORLD_BROWSE to { BottleWorldContainerFragment(TAB_BOTTLE_WORLD_BROWSE) },
        TAB_BOTTLE_WORLD_FOLLOWER to { BottleWorldContainerFragment(TAB_BOTTLE_WORLD_FOLLOWER) },
        TAB_BOTTLE_WORLD_FOLLOWING to { BottleWorldContainerFragment(TAB_BOTTLE_WORLD_FOLLOWING) }
    )

    override fun getItemCount(): Int = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

    companion object {
        const val TAB_BOTTLE_WORLD_BROWSE = 0
        const val TAB_BOTTLE_WORLD_FOLLOWER = 1
        const val TAB_BOTTLE_WORLD_FOLLOWING = 2
    }
}