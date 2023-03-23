package com.wyb.wyb_android.ui.userhome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentUserHomeBinding
import com.wyb.wyb_android.widget.WYBToast

class UserHomeFragment :
    ViewModelFragment<FragmentUserHomeBinding, UserHomeViewModel>(R.layout.fragment_user_home) {
    override val viewModel: UserHomeViewModel by viewModels()

    private val args: UserHomeFragmentArgs by navArgs()

    private lateinit var challengeAdapter: UserHomeChallengeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchUserHome(args.userId)
        initAdapter()
        setListener()
        initLayout()
    }

    private fun initAdapter() {
        challengeAdapter = UserHomeChallengeAdapter()
        binding.rvChallenge.adapter = challengeAdapter
        viewModel.discomfortList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(challengeAdapter) { submitList(list) }
            }
        }
    }

    private fun setListener() {
        binding.layoutTitle.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.tvTerm.setOnClickListener {
            navigateToCalendar()
        }
        binding.layoutCalender.setOnClickListener {
            navigateToCalendar()
        }
        binding.layoutCheer.setOnClickListener {
            viewModel.postCheer(args.userId)
            WYBToast.createToast(
                requireContext(),
                getString(R.string.notification_toast_cheer, viewModel.nickname.value)
            ).show()
        }
        binding.cbFollow.setOnClickListener {
            viewModel.postFollow(args.userId)
        }
    }

    private fun initLayout() {
        viewModel.challengeComfort.observe(viewLifecycleOwner) { comfort ->
            binding.layoutChallengeComfort.setComfortTitleText(comfort)
        }
    }

    private fun navigateToCalendar() {
        findNavController().navigate(
            UserHomeFragmentDirections.actionUserHomeToCalendar(
                userId = args.userId
            )
        )
    }
}