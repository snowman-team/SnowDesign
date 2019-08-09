package com.xueqiu.design.dialog

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import com.xueqiu.design.R
import com.xueqiu.design.inflate
import com.xueqiu.design.isActivityActive
import java.lang.ref.WeakReference

class SnowDialog private constructor(val context: Context?, val builder: Builder) {

    private var mDialog: AlertDialog? = null

    fun show() {
        if (null == context) return
        if (!context.isActivityActive()) {
            return
        }
        if (null != mDialog) {
            mDialog = null
        }
        val dialog = AlertDialog.Builder(context, builder.styleRes)
        dialog.setView(context.inflate(builder.layoutID, null))
        builder.listener?.let { listener ->
            dialog.setOnCancelListener {
                listener.onCancel()
            }
            dialog.setOnDismissListener {
                listener.onFinish()
            }
        }
        mDialog = dialog.show()
    }

    fun dismiss() {
        if (!context.isActivityActive()) {
            return
        }
        mDialog?.dismiss()
        mDialog = null
    }

    class Builder(context: Context, @LayoutRes val layoutID: Int) {

        private var mContextWeak: WeakReference<Context>? = null

        var styleRes: Int = R.style.DesignSnowball_Dialog
            private set

        var listener: DialogListener? = null
            private set

        init {
            mContextWeak = WeakReference(context)
        }

        fun withStyle(@StyleRes styleRes: Int): Builder {
            this.styleRes = styleRes
            return this
        }

        fun withListener(listener: DialogListener): Builder {
            this.listener = listener
            return this
        }

        fun build(): SnowDialog {
            return SnowDialog(mContextWeak?.get(), this)
        }

    }

}