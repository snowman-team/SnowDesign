package com.xueqiu.design.dialog

import android.content.Context
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.xueqiu.design.R
import com.xueqiu.design.getAttrColor
import com.xueqiu.design.inflate
import com.xueqiu.design.setColorFilter
import com.xueqiu.design.isActivityActive
import java.lang.ref.WeakReference

class SnowProgressDialog private constructor(val context: Context?, val builder: Builder) {

    private var mDialog: AlertDialog? = null

    fun show() {
        if (null == context) return
        if (!context.isActivityActive()) {
            return
        }
        if (null != mDialog) {
            mDialog = null
        }
        val dialog = AlertDialog.Builder(context, R.style.DesignSnowball_Dialog_Progress)
        val progress = context.inflate(R.layout.design_dialog_progress) as ProgressBar
        progress.indeterminateDrawable?.setColorFilter(context.getAttrColor(R.attr.designColorPositive))
        dialog.setView(progress)
        mDialog = dialog.show()
    }

    fun dismiss() {
        if (!context.isActivityActive()) {
            return
        }
        mDialog?.dismiss()
        mDialog = null
    }

    class Builder(context: Context) {

        private var mContextWeak: WeakReference<Context>? = null

        init {
            mContextWeak = WeakReference(context)
        }


        fun build(): SnowProgressDialog {
            return SnowProgressDialog(mContextWeak?.get(), this)
        }

    }

}