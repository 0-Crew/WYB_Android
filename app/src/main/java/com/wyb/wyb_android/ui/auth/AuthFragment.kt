package com.wyb.wyb_android.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kakao.sdk.user.UserApiClient
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.ViewModelFragment
import com.wyb.wyb_android.data.type.AuthType
import com.wyb.wyb_android.databinding.FragmentAuthBinding
import com.wyb.wyb_android.ui.MainActivity
import com.wyb.wyb_android.ui.auth.AuthActivity.Companion.TAG
import com.wyb.wyb_android.util.safeNavigate

class AuthFragment : ViewModelFragment<FragmentAuthBinding, AuthViewModel>(
    R.layout.fragment_auth
) {
    override val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor = requireContext().getColor(R.color.orange)
        initClickListener()
    }

    private fun initClickListener() {
        binding.layoutOnBoarding.setOnClickListener {
            findNavController().navigate(R.id.actionAuthToOnBoardingIntro)
        }
        binding.layoutKakao.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                    if (error != null) {
                        Log.e(TAG, "로그인 실패", error)
                    } else if (token != null) {
                        successKakaoLogin(token.accessToken)
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(requireContext()) { token, error ->
                    if (error != null) {
                        Log.e(TAG, "로그인 실패", error)
                    } else if (token != null) {
                        successKakaoLogin(token.accessToken)
                    }
                }
            }
        }
    }

    private fun successKakaoLogin(token: String) {
        UserApiClient.instance.accessTokenInfo { _, _ ->
            UserApiClient.instance.me { user, error ->
                val email = user?.kakaoAccount?.email
                if (email != null) {
                    viewModel.postAuth(email, token)
                    viewModel.authType.observe(viewLifecycleOwner) { type ->
                        when (type) {
                            AuthType.LOGIN -> {
                                startActivity(Intent(requireContext(), MainActivity::class.java))
                                requireActivity().finish()
                            }
                            AuthType.SIGNUP -> findNavController().safeNavigate(R.id.actionAuthToNickname)
                        }
                    }
                } else {
                    Log.e(TAG, "로그인 실패", error)
                }
            }
        }
    }
}