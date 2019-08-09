package com.xueqiu.design.web

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.webkit.*
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.xueqiu.design.R
import com.xueqiu.design.dpToPx
import com.xueqiu.design.getAttrColor
import com.xueqiu.design.setColorFilter

class SnowWebView : FrameLayout {

    private val mWebView: WebView
    private val mProgressBar: ProgressBar

    var url: String = ""
        get() = mWebView.url
        set(value) {
            field = value
            mWebView.loadUrl(field)
        }

    var listener: WebViewListener? = null

    var enableJavaScript: Boolean = false
        set(value) {
            field = value
            mWebView.settings.javaScriptEnabled = enableJavaScript
        }

    var userAgent: String = ""
        set(value) {
            field = value
            mWebView.settings.userAgentString = field
        }

    var encodeFormat: String = "utf-8"
        set(value) {
            field = value
            mWebView.settings.defaultTextEncodingName = "utf-8"
        }

    var pageTitle: String? = null
        private set

    var progressColor: Int = Color.TRANSPARENT
        set(value) {
            field = value
            mProgressBar.progressDrawable.setColorFilter(field)
        }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        mWebView = WebView(context)
        mWebView.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        mWebView.setBackgroundColor(Color.TRANSPARENT)
        addView(mWebView)

        mProgressBar = ProgressBar(context, attrs, android.R.attr.progressBarStyleHorizontal)
        mProgressBar.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            context.dpToPx(2f).toInt()
        )
        addView(mProgressBar)

        initParams(context, attrs)

        initWebView()
    }

    private fun initParams(context: Context, attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SnowWebView,
            0,
            R.style.DesignTheme
        ).apply {
            try {
                progressColor = getColor(
                    R.styleable.SnowWebView_progress_color,
                    context.getAttrColor(R.attr.colorPrimary)
                )
            } finally {
                recycle()
            }
        }
    }

    private fun initWebView() {

        mWebView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                listener?.onPageStarted()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                listener?.onPageFinished()
            }

            @TargetApi(Build.VERSION_CODES.M)
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                val url: String? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    request?.url?.toString()
                } else {
                    ""
                }
                var errorCode: Int? = null
                var desc: String? = null
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    errorCode = error?.errorCode
                    desc = error?.description?.toString()
                }
                listener?.onPageError(url, errorCode, desc, request?.isForMainFrame ?: false)
            }

            @Suppress("DEPRECATION")
            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    listener?.onPageError(failingUrl, errorCode, description, true)
                }
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                listener?.onPageHttpError(view, request, errorResponse)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    return listener?.onPageRedirect(url) ?: false
                }
                return false // in case call twice
            }

            @TargetApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return listener?.onPageRedirect(request?.url?.toString()) ?: false
            }
        }

        mWebView.webChromeClient = object : WebChromeClient() {

            override fun onReceivedTitle(view: WebView?, title: String?) {
                pageTitle = title
                listener?.onReceivedTitle(title)
            }

            override fun onProgressChanged(view: WebView?, progress: Int) {
                mProgressBar.visibility = if (progress == 0 || progress == 100) View.GONE else View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mProgressBar.setProgress(progress, true)
                } else {
                    mProgressBar.progress = progress
                }
                listener?.onProgressChanged(progress)
            }

            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                return listener?.onJsAlert(view, url, message, result) ?: false
            }

            override fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                return listener?.onJsConfirm(view, url, message, result) ?: false
            }

            override fun onJsPrompt(
                view: WebView?,
                url: String?,
                message: String?,
                defaultValue: String?,
                result: JsPromptResult?
            ): Boolean {
                return listener?.onJsPrompt(view, url, message, defaultValue, result) ?: false
            }

            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                return listener?.onShowFileChooser(webView, filePathCallback, fileChooserParams)
                    ?: super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
            }
        }

        mWebView.setDownloadListener { url, userAgent, contentDisposition, mimeType, contentLength ->
            listener?.onDownload(url, userAgent, contentDisposition, mimeType, contentLength)
        }
    }

    fun getSettings(): WebSettings? = mWebView.settings

    fun postUrl(url: String, postData: ByteArray) = mWebView.postUrl(url, postData)

    @SuppressLint("AddJavascriptInterface")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun setJsBridge(jsBridge: BaseJsBridge, name: String) {
        if (enableJavaScript) {
            mWebView.addJavascriptInterface(jsBridge, name)
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun evaluateJavascript(script: String, resultCallback: ValueCallback<String>) {
        mWebView.evaluateJavascript(script, resultCallback)
    }

    fun back(): Boolean {
        if (mWebView.canGoBack()) {
            mWebView.goBack()
            return true
        }
        return false
    }

    fun reload() {
        mWebView.reload()
    }


}