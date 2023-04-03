package com.wyb.wyb_android.ui.auth.nickname

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentNicknameBinding
import com.wyb.wyb_android.ui.MainActivity

class NicknameFragment : ViewModelFragment<FragmentNicknameBinding, NicknameViewModel>(
    R.layout.fragment_nickname
) {
    override val viewModel: NicknameViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor =
            requireContext().getColor(android.R.color.transparent)
        addListener()
    }

    private fun addListener() {
        binding.layoutNickname.setOnFocusChangeListener(requireActivity())
        binding.btnConfirm.setOnClickListener {
            viewModel.postNickname()
            viewModel.isNickNameValid.observe(viewLifecycleOwner) { isValid ->
                if (isValid) {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                }
            }
        }
    }
}