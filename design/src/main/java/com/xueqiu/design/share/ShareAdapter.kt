package com.xueqiu.design.share

import com.smilehacker.lego.LegoAdapter

class ShareAdapter : LegoAdapter() {

    var callback: Callback? = null

    init {

        setDiffUtilEnabled(true)

        val shareItemComponent = ShareItemComponent()
        shareItemComponent.listener = object : ShareItemComponent.ShareItemListener {

            override fun onShareItemClick(type: String) {
                callback?.onShareItemClick(type)
            }
        }
        register(shareItemComponent)
    }

    interface Callback {
        fun onShareItemClick(type: String)
    }

}