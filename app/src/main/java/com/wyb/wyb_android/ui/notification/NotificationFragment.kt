package com.wyb.wyb_android.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentNotificationBinding
import com.wyb.wyb_android.widget.WYBToast

class NotificationFragment :
    ViewModelFragment<FragmentNotificationBinding, NotificationViewModel>(R.layout.fragment_notification) {
    override val viewModel: NotificationViewModel by viewModels()
    private lateinit var notificationAdapter: NotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        initRecyclerView()
        addListener()
        return binding.root
    }

    private fun initRecyclerView() {
        notificationAdapter = NotificationAdapter()
        binding.rvNotification.adapter = notificationAdapter
        binding.rvNotification.layoutManager = LinearLayoutManager(requireContext())
        notificationAdapter.submitList(viewModel.notificationItems)
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        notificationAdapter.setTextClickListener(object : NotificationAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val item = viewModel.notificationItems[position]
                when (item.notificationName) {
                    "cheer" -> {
                        if (item.isButtonEnabled) {
                            val action = NotificationFragmentDirections.actionNotificationToHome(item.userId)
                            findNavController().navigate(action)
                        } else {
                            val action =
                                NotificationFragmentDirections.actionNotificationToCheer(item.sentUser.name)
                            findNavController().navigate(action)
                        }
                    }
                    "congrats" -> {
                        if (item.isButtonEnabled) {
                            val action = NotificationFragmentDirections.actionNotificationToHome(item.userId)
                            findNavController().navigate(action)
                        } else {
                            val action =
                                NotificationFragmentDirections.actionNotificationToCeleb(item.sentUser.name)
                            findNavController().navigate(action)
                        }
                    }
                    else -> {
                        val action = NotificationFragmentDirections.actionNotificationToHome(item.userId)
                        findNavController().navigate(action)
                    }
                }
            }
        })

        notificationAdapter.setButtonClickListener(object :
            NotificationAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val item = viewModel.notificationItems[position]
                when (item.notificationName) {
                    "cheer" -> {
                        WYBToast.createToast(
                            requireContext(),
                            getString(R.string.notification_toast_cheer, item.sentUser.name)
                        ).show()
                    }
                    else -> {
                        WYBToast.createToast(
                            requireContext(),
                            getString(R.string.notification_toast_celeb, item.sentUser.name)
                        ).show()
                    }
                }
            }
        })
    }
}