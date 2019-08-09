package com.xueqiu.design.app

import android.content.Context
import android.content.SharedPreferences

object Store {

    private var mStore: SharedPreferences? = null

    fun init(appContext: Context) {
        mStore = appContext.getSharedPreferences("app", Context.MODE_PRIVATE)
    }

    fun getStore() = mStore

    fun applyTheme(context: Context) {

        when (mStore?.getInt("theme", 0)) {
            0 -> {
                context.setTheme(R.style.SnowballTheme_Sapphire)
            }
            2 -> {
                context.setTheme(R.style.SnowballTheme_Amber)
            }
        }
    }

}