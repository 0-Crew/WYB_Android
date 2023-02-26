package com.wyb.wyb_android.ui.notification

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.BindingFragment
import com.wyb.wyb_android.databinding.FragmentNotificationCheerBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotificationCheerFragment :
    BindingFragment<FragmentNotificationCheerBinding>(R.layout.fragment_notification_cheer) {
    private val args: NotificationCheerFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setTimer()
    }

    private fun initView() {
        binding.tvCheer.text =
            requireContext().getString(R.string.notification_challenge_cheer, args.userName)
    }

    private fun setTimer() {
        lifecycleScope.launch {
            delay(3000)
            findNavController().popBackStack()
        }
    }
}