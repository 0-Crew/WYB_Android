package com.wyb.wyb_android.ui.auth.nickname

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.databinding.FragmentNicknameBinding
import com.wyb.wyb_android.ui.MainActivity
import com.wyb.wyb_android.ui.setting.SettingViewModel.Companion.MAX_NICKNAME_LENGTH
import kotlinx.android.synthetic.main.view_wyb_label_edit_text.view.*

class NicknameFragment : ViewModelFragment<FragmentNicknameBinding, NicknameViewModel>(
    R.layout.fragment_nickname
) {
    override val viewModel: NicknameViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor =
            requireContext().getColor(android.R.color.transparent)
        initView()
        addListener()
    }

    private fun initView() {
        with(binding.layoutNickname) {
            setEditTextFocusable()
            setTextInputFilter()
            setTextMaxLength(MAX_NICKNAME_LENGTH)
            etInput.setImeActionLabel(
                getString(R.string.complete),
                android.view.inputmethod.EditorInfo.IME_ACTION_DONE
            )
        }
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