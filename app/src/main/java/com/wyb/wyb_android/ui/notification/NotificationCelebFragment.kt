package com.wyb.wyb_android.ui.notification

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.BindingFragment
import com.wyb.wyb_android.databinding.FragmentNotificationCelebBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotificationCelebFragment :
    BindingFragment<FragmentNotificationCelebBinding>(R.layout.fragment_notification_celeb) {
    private val args: NotificationCelebFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setTimer()
    }

    private fun initView() {
        binding.tvCeleb.text =
            requireContext().getString(R.string.notification_challenge_celeb, args.userName)
    }

    private fun setTimer() {
        lifecycleScope.launch {
            delay(3000)
            findNavController().popBackStack()
        }
    }
}