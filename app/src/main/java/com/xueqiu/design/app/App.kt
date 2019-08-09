package com.xueqiu.design.app

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Store.init(this)
        Store.applyTheme(this)
    }
}