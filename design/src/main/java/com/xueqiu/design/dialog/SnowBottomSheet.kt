package com.xueqiu.design.dialog

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.xueqiu.design.R
import com.xueqiu.design.isActivityActive

abstract class SnowBottomSheet(val context: Context) {

    protected var bottomSheet: BottomSheetDialog? = null

    var listener: DialogListener? = null

    open fun show(styleRes: Int = R.style.DesignSnowball_BottomSheet) {
        if (!context.isActivityActive()) {
            return
        }
        bottomSheet = BottomSheetDialog(context, styleRes)
        bottomSheet?.setContentView(getStuffView())
        bottomSheet?.setOnCancelListener {
            listener?.onCancel()
        }

        bottomSheet?.setOnDismissListener {
            listener?.onFinish()
        }

        bottomSheet?.setOnShowListener {
            listener?.onShow()
        }
        if (context.isActivityActive()) {
            bottomSheet?.show()
            onViewAttached()
        }
    }

    open fun dismiss() {
        if (context.isActivityActive()) {
            bottomSheet?.dismiss()
        }
        bottomSheet = null
    }

    @Throws(Exception::class)
    fun setBackgroundTransparent() {
        bottomSheet?.window?.findViewById<ViewGroup>(R.id.design_bottom_sheet)?.setBackgroundResource(android.R.color.transparent)
    }

    abstract fun getStuffView(): View

    abstract fun onViewAttached()

    fun setCanceledTouchOutside(boolean: Boolean) {
        bottomSheet?.setCanceledOnTouchOutside(boolean)
    }

}

