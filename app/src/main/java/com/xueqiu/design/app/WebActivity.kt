package com.xueqiu.design.app

import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.xueqiu.design.web.SnowWebView
import com.xueqiu.design.web.WebViewListener
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.common_toolbar.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        toolbar.enableBackNav(View.OnClickListener {
            finish()
        })
        toolbar.title = "WebPage"

        testWebView()
    }

    private fun testWebView() {
        val webView = findViewById<SnowWebView>(R.id.view_web)
        webView.listener = object : WebViewListener {

            override fun onPageError(url: String?, errorCode: Int?, msg: String?, isMainFrame: Boolean) {

            }

            override fun onPageStarted() {
            }


            override fun onPageHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
            }

            override fun onPageRedirect(url: String?): Boolean {
                return true
            }

        }
        edt_url.text = "https://github.com"
        btn_confirm.setOnClickListener {
            webView.url = edt_url.text
        }
    }

}