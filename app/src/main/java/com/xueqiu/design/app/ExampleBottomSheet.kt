package com.xueqiu.design.app

import android.content.Context
import android.view.View
import com.xueqiu.design.dialog.SnowBottomSheet

class ExampleBottomSheet(context: Context) : SnowBottomSheet(context) {

    override fun getStuffView(): View {
        return View.inflate(context, R.layout.view_example, null)
    }

    override fun onViewAttached() {
        setBackgroundTransparent()
    }

}