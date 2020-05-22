package com.puxiansheng.www.ui.info

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentInfoDetailBinding

class InfoDetailFragment : Fragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentInfoDetailBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        buttonBack.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeNavHost).navigateUp()
        }

        infoDetail.apply {
            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    view?.loadUrl(request?.url.toString())
                    return true
                }

                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    super.onReceivedSslError(view, handler, error)
                    handler?.proceed()
                }
            }
            loadUrl(arguments?.getString("url"))
        }.settings.apply {
            javaScriptEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
            userAgentString
            setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW)
        }
    }.root
}