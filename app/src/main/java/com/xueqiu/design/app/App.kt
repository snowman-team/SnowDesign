package com.xueqiu.design.app

import android.app.Application
import com.smilehacker.lego.Lego
import com.smilehacker.lego.factory.LegoFactory_design

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Store.init(this)
        Store.applyTheme(this)

        Lego.addFactory(LegoFactory_design::class.java)
    }
}