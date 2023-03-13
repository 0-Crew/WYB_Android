package com.wyb.wyb_android.ui.otherspage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentOthersPageBinding

class OthersPageFragment :
    ViewModelFragment<FragmentOthersPageBinding, OthersPageViewModel>(R.layout.fragment_others_page) {
    override val viewModel: OthersPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        initLayout()
    }

    private fun setListener() {
        binding.layoutTitle.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initLayout() {
        viewModel.challengeComfort.observe(viewLifecycleOwner) { comfort ->
            binding.layoutChallengeComfort.setComfortTitleText(comfort)
        }
    }
}