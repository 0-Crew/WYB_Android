package com.wyb.wyb_android.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.BindingFragment
import com.wyb.wyb_android.data.model.Challenge
import com.wyb.wyb_android.data.model.Comfort
import com.wyb.wyb_android.databinding.FragmentChallengeBinding

class ChallengeFragment : BindingFragment<FragmentChallengeBinding>(R.layout.fragment_challenge) {
    private val challengeViewModel by viewModels<ChallengeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.lifecycleOwner = this@ChallengeFragment
        setChallengeVPAdapter()
        setTabMediator()
        return binding.root
    }

    private fun setChallengeVPAdapter() {

        val itemList = mutableListOf(
            Comfort(
                "11.06-12.3",
                "편리함1",
                mutableListOf(
                    Challenge("불편함1", "06", isToday = false, isFuture = false),
                    Challenge("불편함1", "07", isToday = false, isFuture = false),
                    Challenge("불편함1", "08", isToday = false, isFuture = false),
                    Challenge("불편함1", "09", isToday = false, isFuture = false),
                    Challenge("불편함1", "10", isToday = true, isFuture = false),
                    Challenge("불편함1", "11", isToday = false, isFuture = true),
                    Challenge("불편함1", "12", isToday = false, isFuture = true)
                )
            ),
            Comfort(
                "123",
                "편리함12",
                mutableListOf(

                )
            ), Comfort(
                "456",
                "편리함13",
                mutableListOf(

                )
            )
        )

        binding.viewPagerContent.adapter = ChallengeAdapter(challengeViewModel, itemList)
        binding.viewPagerContent.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPagerContent.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun setTabMediator() {
        TabLayoutMediator(binding.tabLayoutChallengeContent, binding.viewPagerContent) { tab, _ ->
            tab.text = "나"
        }.attach()
    }
}