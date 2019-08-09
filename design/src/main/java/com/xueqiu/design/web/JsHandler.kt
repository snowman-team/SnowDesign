package com.xueqiu.design.web

import org.json.JSONObject

abstract class JsHandler {

    @Throws(Exception::class)
    fun handle(params: String, promise: Promise) {
        var targetParams = params
        if (targetParams.isEmpty()) {
            targetParams = "{}"
        }
        handle(JSONObject(targetParams), promise)
    }

    @Throws(Exception::class)
    abstract fun handle(params: JSONObject, promise: Promise)

    abstract class Promise {

        abstract fun resolve(res: String)

        abstract fun reject(error: String)

    }
}
