package com.xueqiu.design.web

import android.webkit.JavascriptInterface
import java.util.*

abstract class BaseJsBridge(protected val webView: SnowWebView) {

    companion object {

        const val ERROR_INVALID_HOST = 1001

        const val ERROR_NULL_HANDLER = 1002
    }

    protected val mHandlers = HashMap<String, JsHandler>()

    @JavascriptInterface
    fun execute(methodName: String, params: String, callBackName: String) {
        val url = webView.url
        webView.post {
            if (isValidHost(url)) {
                val handler = mHandlers[methodName]
                if (null == handler) {
                    onError(ERROR_NULL_HANDLER, "Execute $methodName with null handler")
                } else {
                    handleMethod(handler, params, callBackName)
                }
            } else {
                onError(ERROR_INVALID_HOST, "Execute $methodName with invalid host $url")
            }
        }
    }

    fun register(methodName: String, jsHandler: JsHandler) {
        mHandlers[methodName] = jsHandler
    }

    abstract fun isValidHost(url: String): Boolean

    abstract fun onError(code: Int?, msg: String?)

    abstract fun handleMethod(handler: JsHandler, params: String, callBackName: String)
}