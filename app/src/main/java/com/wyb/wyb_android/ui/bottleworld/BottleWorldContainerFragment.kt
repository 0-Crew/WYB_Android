package com.wyb.wyb_android.ui.bottleworld

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentBottleWorldContainerBinding

class BottleWorldContainerFragment :
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
        setBrowseList()
    }

    private fun initRVAdapter() {
        binding.rvBottleWorld.adapter = bottleWorldListAdapter
    }

    private fun setBrowseList() {
        viewModel.browseList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(bottleWorldListAdapter) { submitList(list) }
            }
        }
    }
}