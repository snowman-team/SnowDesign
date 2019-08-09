package com.xueqiu.design.web

import android.net.Uri
import android.webkit.*

interface WebViewListener {

    fun onPageStarted()

    fun onPageError(url: String?, errorCode: Int?, msg: String?, isMainFrame: Boolean)

    fun onPageHttpError(
        view: WebView?,
        request: WebResourceRequest?,
        errorResponse: WebResourceResponse?
    )

    fun onPageRedirect(url: String?): Boolean

    fun onPageFinished() {}

    fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: WebChromeClient.FileChooserParams?
    ): Boolean = false

    fun onDownload(url: String?, userAgent: String?, desc: String?, mimeType: String?, contentLength: Long?) {}

    fun onReceivedTitle(title: String?) {}

    fun onProgressChanged(progress: Int) {}

    fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean = false

    fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean = false

    fun onJsPrompt(view: WebView?, url: String?, msg: String?, default: String?, result: JsPromptResult?): Boolean =
        false

}