package com.wyb.wyb_android.ui.setting

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wyb.wyb_android.R
import com.wyb.wyb_android.base.BindingFragment
import com.wyb.wyb_android.databinding.FragmentSettingTermsBinding

class SettingTermsFragment : BindingFragment<FragmentSettingTermsBinding>(
    R.layout.fragment_setting_terms
) {
    private val args: SettingTermsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        initWebView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        binding.layoutTitle.titleString = getString(args.termsType.title)

        val url = context?.getString(args.termsType.url).orEmpty()
        binding.layoutWebView.apply {
            loadUrl(url)
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding.layoutProgress.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.layoutProgress.visibility = View.GONE
                }
            }
        }
    }

    private fun addListener() {
        binding.layoutTitle.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        binding.layoutWebView.destroy()
        super.onDestroyView()
    }
}